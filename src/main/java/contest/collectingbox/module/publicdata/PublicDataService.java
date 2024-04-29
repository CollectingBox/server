package contest.collectingbox.module.publicdata;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import contest.collectingbox.module.collectingbox.domain.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.domain.Tag;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublicDataService {

    private static final String CSV_FILE_PATH = "csv/";
    private final PublicDataApiInfoRepository publicDataApiInfoRepository;
    private final PublicDataExtract publicDataExtract;
    private final KakaoApiManager kakaoApiManager;
    private final CollectingBoxRepository collectingBoxRepository;

    public void savePublicDataApiInfo(List<SavePublicDataApiInfoRequest> requests) {
        for (SavePublicDataApiInfoRequest request : requests) {
            publicDataApiInfoRepository.save(request.toEntity());
        }
    }

    public long loadPublicData(JSONObject jsonObject, Tag tag) {
        long loadedDataCount = 0;
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");

        Set<String> querySet = new HashSet<>();
        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            querySet.add(publicDataExtract.extractQuery(object));
        }

        for (String query : querySet) {
            // 검색 키워드 null 체크
            if (query == null) {
                continue;
            }

            // 카카오 주소 검색 API 호출
            AddressInfoResponse response = kakaoApiManager.fetchAddressInfo(query, tag);

            // 카카오 주소 검색 API 응답 null 체크
            if (response == null) {
                continue;
            }

            if (response.hasNull()) {
                throw new RuntimeException("null");
            }

            // 카카오 주소 검색 API 응답 출력
            log.info("query = {}, response = {}", query, response);

            // insert DB
            loadedDataCount++;
            collectingBoxRepository.save(response.toEntity());
        }

        return loadedDataCount;
    }

    public long loadCsvPublicData(LoadCsvPublicDataRequest request) {
        String fileName = CSV_FILE_PATH + request.getFileName();
        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)), "EUC-KR"));

            int columnIndex = publicDataExtract.extractCsvQueryIndex(csvReader.readNext());
            if (columnIndex == -1) {
                return 0;
            }
            return saveCsvPublicData(csvReader, columnIndex, request.getTag());
        } catch (IOException | CsvValidationException e) {
            log.error("Fail loading CSV file: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private long saveCsvPublicData(CSVReader csvReader, int index, Tag tag) throws CsvValidationException, IOException {
        long dataCount = 0;
        String[] line;
        Set<String> querySet = new HashSet<>();
        while ((line = csvReader.readNext()) != null) {
            String query = line[index];
            if (querySet.contains(query)) {
                continue;
            }
            querySet.add(query);

            if (query == null || query.isEmpty()) {
                continue;
            }

            AddressInfoResponse response = kakaoApiManager.fetchAddressInfo(query, tag);
            log.info("query = {}, response = {}", query, response);

            if (response != null) {
                dataCount++;
                collectingBoxRepository.save(response.toEntity());
            }

        }
        return dataCount;
    }
}
