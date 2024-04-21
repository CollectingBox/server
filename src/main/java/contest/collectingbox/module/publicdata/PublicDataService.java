package contest.collectingbox.module.publicdata;

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

    public long loadPublicData(JSONObject jsonObject) {
        long loadedDataCount = 0;
        JSONArray jsonArray = (JSONArray) jsonObject.get("data");

        Set<String> querySet = new HashSet<>();
        for (Object o : jsonArray) {
            JSONObject object = (JSONObject) o;
            querySet.add(publicDataExtract.extractQuery(object));
        }

        for (String query : querySet) {
            System.out.println(query);
            loadedDataCount++;

            // 주소 검색 API 호출(query)
            // DTO 매핑
        }

        return loadedDataCount;
    }
}
