package contest.collectingbox.module.collectingbox.domain;

import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.global.exception.ErrorCode;

import java.util.Collections;
import java.util.List;

public class Tags {
    private final List<Tag> tags;

    public Tags(List<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            throw new CollectingBoxException(ErrorCode.NOT_SELECTED_TAG);
        }
        this.tags = tags;
    }

    public List<Tag> toUnmodifiableList() {
        return Collections.unmodifiableList(tags);
    }
}
