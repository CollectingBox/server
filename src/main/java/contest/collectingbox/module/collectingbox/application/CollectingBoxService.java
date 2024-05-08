package contest.collectingbox.module.collectingbox.application;

import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.global.exception.ErrorCode;
import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.location.domain.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static contest.collectingbox.global.exception.ErrorCode.NOT_FOUND_COLLECTING_BOX;

@Service
@RequiredArgsConstructor
public class CollectingBoxService {

    private final CollectingBoxRepository collectingBoxRepository;
    private final LocationRepository locationRepository;

    @Value("${collecting-box.search.radius.meter}")
    private int radius;

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> findCollectingBoxesWithinArea(final Double latitude,
                                                                     final Double longitude,
                                                                     final List<Tag> tags) {
        if (tags.isEmpty()) {
            throw new CollectingBoxException(ErrorCode.NOT_SELECTED_TAG);
        }

        Point center = GeometryUtil.toPoint(longitude, latitude);

        return collectingBoxRepository.findAllWithinArea(center, radius, tags)
                .stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CollectingBoxDetailResponse findBoxDetailById(Long collectionId) {
        return collectingBoxRepository.findDetailById(collectionId);
    }

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> searchCollectingBoxes(final String query, final List<Tag> tags) {
        if (tags.isEmpty()) {
            throw new CollectingBoxException(ErrorCode.NOT_SELECTED_TAG);
        }

        String dong = locationRepository.findDongByKeyword(query);

        if (dong == null) {
            return collectingBoxRepository.findAllBySigungu(query, toString(tags))
                    .stream()
                    .map(CollectingBoxResponse::fromEntity)
                    .collect(Collectors.toList());
        }

        return collectingBoxRepository.findAllByDong(dong, toString(tags))
                .stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private List<String> toString(List<Tag> tags) {
        return tags.stream().map(Enum::name).collect(Collectors.toList());
    }
}
