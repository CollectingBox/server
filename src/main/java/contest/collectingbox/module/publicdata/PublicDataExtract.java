package contest.collectingbox.module.publicdata;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
                if (key.contains(keyword) && !jsonObject.get(key).toString().isBlank()) {
                    return jsonObject.get(key).toString();
                }
            }
        }

        log.error("Not contains anything!!!");

        return null;
    }
}

