package contest.collectingbox.module.collectingbox.domain;

import contest.collectingbox.global.exception.CollectingBoxException;

import java.util.List;

import static contest.collectingbox.global.exception.ErrorCode.NOT_SELECTED_TAG;

public class Tags {
    private final List<Tag> tags;

    public Tags(List<Tag> tags) {
        if (tags == null || tags.isEmpty()) {
            throw new CollectingBoxException(NOT_SELECTED_TAG);
        }
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return List.copyOf(tags);
    }
}
