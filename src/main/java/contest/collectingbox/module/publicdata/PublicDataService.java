package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublicDataService {

    private final PublicDataExtract publicDataExtract;
    private final KakaoApiManager kakaoApiManager;


    public long loadPublicData(JSONObject jsonObject, Tag tag) {
        long loadedDataCount = 0;
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");

        Set<String> querySet = new HashSet<>();
        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            querySet.add(publicDataExtract.extractQuery(object));
        }

        for (String query : querySet) {
            loadedDataCount++;

            // query -> kakaoApi -> DTO
            AddressInfoResponse response = kakaoApiManager.fetchAddressInfo(query, tag);
            log.info("query = {}, response = {}", query, response);

            // insert DB
        }

        return loadedDataCount;
    }
}
