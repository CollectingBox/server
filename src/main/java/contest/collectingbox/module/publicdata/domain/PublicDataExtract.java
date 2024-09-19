package contest.collectingbox.module.publicdata.domain;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

import static contest.collectingbox.module.collectingbox.domain.Tag.TRASH;

@Slf4j
@Component
public class PublicDataExtract {

    private static final String[] KEYWORDS = {"도로", "지번", "주소", "소재지", "위치", "장소"};

    public Optional<String> extractQuery(JSONObject jsonObject, String sigungu, Tag tag) {
        Optional<String> specialValue = extractSpecialCaseValue(jsonObject, sigungu, tag);
        if (specialValue.isPresent()) {
            return specialValue;
        }

        Set<String> keySet = jsonObject.keySet();
        for (String keyword : KEYWORDS) {
            for (String key : keySet) {
                if (key.contains(keyword) && !jsonObject.isNull(key)) {
                    return Optional.ofNullable(jsonObject.get(key).toString());
                }
            }
        }

        log.warn("Not contains anything in {}", jsonObject);
        return Optional.empty();
    }

    private Optional<String> extractSpecialCaseValue(JSONObject jsonObject, String sigungu, Tag tag) {
        if (sigungu.equals("강북구") && tag == TRASH) {
            return Optional.of(jsonObject.optString("세부 위치"));
        }
        return Optional.empty();
    }

    public int extractCsvQueryIndex(String[] columnNames, String sigungu, Tag tag) {
        int specialCaseIndex = extractSpecialCaseIndex(sigungu, tag);
        if (specialCaseIndex != -1) {
            return specialCaseIndex;
        }

        for (String keyword : KEYWORDS) {
            for (int i = 0; i < columnNames.length; i++) {
                if (columnNames[i].contains(keyword)) {
                    return i;
                }
            }
        }
        log.warn("No csv column name containing keywords, columnNames = {}", (Object) columnNames);
        return -1;
    }

    private int extractSpecialCaseIndex(String sigungu, Tag tag) {
        if (sigungu.equals("구로구") && tag == TRASH) {
            return 1; // 소재지가로명주소
        }
        return -1;
    }
}
