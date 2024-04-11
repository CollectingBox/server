package contest.collectingbox.module.review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tag {

    EXIST("존재함"),
    DISAPPEAR("사라짐");

    private final String content;
}
