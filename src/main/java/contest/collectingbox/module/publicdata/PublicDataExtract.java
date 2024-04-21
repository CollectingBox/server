package contest.collectingbox.module.publicdata;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class PublicDataExtract {

    private static final String[] KEYWORDS = {"도로", "지번", "주소"};

    public String extractQuery(JSONObject jsonObject) {
        Set<String> keySet = jsonObject.keySet();
        for (String key : keySet) {
            if (StringUtils.containsAny(key, KEYWORDS)) {
                return jsonObject.get(key).toString();
            }
        }
        log.error("Not contains anything!!!");

        return null;
    }
}

