package contest.collectingbox.module.publicdata;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class PublicDataExtract {

    private static final String[] KEYWORDS = {"도로", "지번", "주소", "소재지", "위치", "장소"};

    public String extractQuery(JSONObject jsonObject) {
        Set<String> keySet = jsonObject.keySet();
        for (String keyword : KEYWORDS) {
            for (String key : keySet) {
                if (key.contains(keyword) && !jsonObject.isNull(key)) {
                    return jsonObject.get(key).toString();
                }
            }
        }

        log.error("Not contains anything in {}", jsonObject);

        return null;
    }

    public int extractCsvQueryIndex(String[] columnNames) {
        for (String keyword : KEYWORDS) {
            for (int i = 0; i < columnNames.length; i++) {
                if (columnNames[i].contains(keyword)) {
                    return i;
                }
            }
        }
        log.error("No csv column name containing keywords, columnNames = {}", (Object) columnNames);
        return -1;
    }
}
