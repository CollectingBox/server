package contest.collectingbox.module.collectingbox.application;

import static contest.collectingbox.global.exception.ErrorCode.*;

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

@Service
@RequiredArgsConstructor
public class CollectingBoxService {

    private final CollectingBoxRepository collectingBoxRepository;
    private final LocationRepository locationRepository;

    @Value("${collecting-box.search.radius.meter}")
    private int radius;

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> findCollectingBoxesWithinArea(Double latitude,
                                                                     Double longitude,
                                                                     List<Tag> tags) {
        Point center = GeometryUtil.toPoint(longitude, latitude);

        return collectingBoxRepository.findAllWithinArea(center, radius, tags)
                .stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CollectingBoxDetailResponse findBoxDetailById(Long collectionId) {
        CollectingBox box = collectingBoxRepository.findById(collectionId)
                .orElseThrow(() -> new CollectingBoxException(
                        NOT_FOUND_COLLECTING_BOX));
        return collectingBoxRepository.findDetailById(box.getId());
    }

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> searchCollectingBoxes(String query, List<Tag> tags) {
        List<String> tagStrings = tags.stream().map(Enum::name).collect(Collectors.toList());

        String dong = locationRepository.findDongByKeyword(query);

        if (dong == null) {
            return collectingBoxRepository.findAllByKeyword(query, tagStrings)
                    .stream()
                    .map(CollectingBoxResponse::fromEntity)
                    .collect(Collectors.toList());
        }

        return collectingBoxRepository.findAllByDong(dong, tagStrings)
                .stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
