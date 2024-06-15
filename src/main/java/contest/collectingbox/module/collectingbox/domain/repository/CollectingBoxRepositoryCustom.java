package contest.collectingbox.module.collectingbox.domain.repository;

import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;

import java.util.List;

public interface CollectingBoxRepositoryCustom {
    CollectingBoxDetailResponse findDetailById(Long id);

    List<CollectingBoxResponse> findAllWithinArea(double longitude, double latitude, int radius, List<Tag> tags);
}
