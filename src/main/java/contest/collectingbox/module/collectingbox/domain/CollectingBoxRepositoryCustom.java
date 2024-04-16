package contest.collectingbox.module.collectingbox.domain;

import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;

public interface CollectingBoxRepositoryCustom {
    CollectingBoxDetailResponse findDetailById(Long id);

}
