package contest.collectingbox.module.collectingbox.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {

    CLOTHES("폐의류"),
    LAMP_BATTERY("폐형광등∙건전지"),
    MEDICINE("폐의약품"),
    TRASH("쓰레기통");

    private final String label;
}
