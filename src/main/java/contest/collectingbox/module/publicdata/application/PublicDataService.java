package contest.collectingbox.module.publicdata.application;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.domain.repository.CollectingBoxRepository;
import contest.collectingbox.module.location.domain.repository.DongInfoRepository;
import contest.collectingbox.module.publicdata.domain.*;
import contest.collectingbox.module.publicdata.dto.AddressInfoDto;
import contest.collectingbox.module.publicdata.dto.LoadCsvPublicDataRequest;
import contest.collectingbox.module.publicdata.dto.LoadPublicDataRequest;
import contest.collectingbox.module.publicdata.dto.SavePublicDataApiInfoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublicDataService {

    private static final String CSV_FILE_PATH = "csv/";

    private final PublicDataApiInfoRepository publicDataApiInfoRepository;
    private final OpenDataApiManager openDataApiManager;
    private final PublicDataExtract publicDataExtract;
    private final KakaoApiManager kakaoApiManager;
    private final CollectingBoxRepository collectingBoxRepository;
    private final DongInfoRepository dongInfoRepository;

    @Transactional
    public void savePublicDataApiInfo(List<SavePublicDataApiInfoRequest> requests) {
        publicDataApiInfoRepository.saveAll(getEntities(requests));
    }

    private List<PublicDataApiInfo> getEntities(List<SavePublicDataApiInfoRequest> requests) {
        return requests.stream()
                .map(SavePublicDataApiInfoRequest::toEntity)
                .toList();
    }

    @Transactional
    public long loadPublicData(List<LoadPublicDataRequest> requests) {
        long loadedDataCount = 0;
        for (LoadPublicDataRequest request : requests) {
            log.info("======= {} - {} =======", request.getSigungu(), request.getTag().getLabel());

            String sigungu = request.getSigungu();
            Tag tag = request.getTag();

            int totalCount = openDataApiManager.fetchTotalCount(request.getCallAddress());
            JSONArray jsonArray = openDataApiManager.fetchOpenData(request.getCallAddress(), totalCount);

            Set<String> querySet = new HashSet<>();
            for (Object o : jsonArray) {
                publicDataExtract.extractQuery((JSONObject) o, sigungu, tag)
                        .ifPresent(querySet::add);
            }

            for (String query : querySet) {
                // 카카오 주소 검색 API 호출
                AddressInfoDto addressInfo = kakaoApiManager.fetchAddressInfo(query, tag);

                // 카카오 주소 검색 API 응답 출력
                log.info("query = {}, response = {}", query, addressInfo);

                // insert DB
                if (addressInfo != null && addressInfo.isSigunguEquals(sigungu)) {
                    collectingBoxRepository.save(addressInfo.toCollectingBox(dongInfoRepository));
                    loadedDataCount++;
                }
            }
        }

        return loadedDataCount;
    }

    @Transactional
    public long loadCsvPublicData(LoadCsvPublicDataRequest request) {
        String fileName = CSV_FILE_PATH + request.getFileName();
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)), "EUC-KR"));

            int columnIndex = publicDataExtract.extractCsvQueryIndex(csvReader.readNext(), request.getSigungu(),
                    request.getTag());
            if (columnIndex == -1) {
                return 0;
            }
            return saveCsvPublicData(csvReader, columnIndex, request.getSigungu(), request.getTag());
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("failed to load CSV file", e);
        }
    }

    private long saveCsvPublicData(CSVReader csvReader, int index, String sigungu, Tag tag)
            throws CsvValidationException, IOException {
        long dataCount = 0;
        String[] line;
        Set<String> querySet = new HashSet<>();
        while ((line = csvReader.readNext()) != null) {
            String query = line[index].trim();
            if (querySet.contains(query)) {
                continue;
            }
            querySet.add(query);

            if (query.isBlank()) {
                continue;
            }

            AddressInfoDto response = kakaoApiManager.fetchAddressInfo(query, tag);
            log.info("query = {}, response = {}", query, response);

            if (response == null || response.hasNull() || response.hasEmptyValue()) {
                continue;
            }

            if (equals(response.getSigungu(), sigungu)) {
                dataCount++;
                collectingBoxRepository.save(response.toCollectingBox(dongInfoRepository));
            }
        }

        return dataCount;
    }

    private boolean equals(String sigungu, String originSigungu) {
        return sigungu.equals(originSigungu);
    }
}
