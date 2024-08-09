package contest.collectingbox.module.collectingbox.domain.repository;

import contest.collectingbox.module.collectingbox.domain.Tags;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.location.domain.GeoPoint;

import java.util.List;

public interface CollectingBoxRepositoryCustom {
    CollectingBoxDetailResponse findDetailById(Long id);

    List<CollectingBoxResponse> findAllWithinArea(GeoPoint center, int radius, Tags tags);

    List<CollectingBoxResponse> searchBySigunguNm(String query, Tags tags);

    List<CollectingBoxResponse> searchByDongNm(String query, Tags tags);
}
