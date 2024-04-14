package contest.collectingbox.module.collectingbox.application;

import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectingBoxService {

    private final CollectingBoxRepository collectingBoxRepository;

    @Value("${spatial.reference.id}")
    private int srid;

    @Value("${collecting-box.search.radius.meter}")
    private int radius;

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> findCollectingBoxesWithinArea(Double latitude,
                                                                     Double longitude,
                                                                     List<Tag> tags) {
        Point center = GeometryUtil.toPoint(srid, longitude, latitude);

        return collectingBoxRepository.findAllWithinArea(center, radius, tags)
                .stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
