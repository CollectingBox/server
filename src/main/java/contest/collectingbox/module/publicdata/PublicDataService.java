package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.domain.Tag;
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
}
