package contest.collectingbox.module.collectingbox.domain;

import contest.collectingbox.global.exception.CollectingBoxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TagsTest {

    @Test
    @DisplayName("태그가 선택되지 않은 경우 실패한다.")
    void fail_byTagsIsEmpty() {
        // when, then
        assertThatThrownBy(() -> new Tags(List.of()))
                .isInstanceOf(CollectingBoxException.class);
    }

    @Test
    @DisplayName("tags를 수정하려고 시도하면 실패한다.")
    void fail_byTryModifyTags() {
        // given
        Tags tags = new Tags(List.of(Tag.CLOTHES));

        // when, then
        assertThatThrownBy(() -> tags.getTags().add(Tag.LAMP_BATTERY))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}