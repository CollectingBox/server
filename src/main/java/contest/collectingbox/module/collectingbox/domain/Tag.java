package contest.collectingbox.module.collectingbox.domain;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {

    CLOTHES("폐의류"),
    LAMP("폐형광등"),
    BATTERY("폐건전지"),
    MEDICINE("폐의약품"),
    TRASH("쓰레기통");

    private final String label;

    public static String of(final String text) {
        Tag tag = Arrays.stream(values())
                .filter(val -> val.name().equals(text))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        return tag.getLabel();
    }
}
