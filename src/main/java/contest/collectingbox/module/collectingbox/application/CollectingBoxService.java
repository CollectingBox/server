package contest.collectingbox.module.collectingbox.application;

import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.domain.Tags;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.location.domain.DongInfo;
import contest.collectingbox.module.location.domain.DongInfoRepository;
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
    private final DongInfoRepository dongInfoRepository;

    @Value("${collecting-box.search.radius.meter}")
    private int radius;

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> findCollectingBoxesWithinArea(final Double latitude,
                                                                     final Double longitude,
                                                                     final Tags tags) {
        Point center = GeometryUtil.toPoint(longitude, latitude);
        return collectingBoxRepository.findAllWithinArea(center, radius, tags.toUnmodifiableList())
                .stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CollectingBoxDetailResponse findBoxDetailById(Long collectionId) {
        return collectingBoxRepository.findDetailById(collectionId);
    }

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> searchCollectingBoxes(final String query, final Tags tags) {
        // '강남구'
        if (query.endsWith("구")) {
            return searchBySigunguNm(query, tags.toUnmodifiableList());
        }

        String[] splitQuery = query.split(" ");

        // '역삼1동'
        if (splitQuery.length == 1) {
            return searchByDongNm(query, tags.toUnmodifiableList());
        }

        // '강남구 역삼1동'
        return searchByDongNm(splitQuery[1], tags.toUnmodifiableList());
    }

    private List<CollectingBoxResponse> searchBySigunguNm(String query, List<Tag> tags) {
        return dongInfoRepository.findAllBySigunguNm(query).stream()
                .flatMap(dongInfo -> collectingBoxRepository.findAllByDongInfoAndTags(dongInfo, tags).stream())
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private List<CollectingBoxResponse> searchByDongNm(String dongNm, List<Tag> tags) {
        DongInfo dongInfo = dongInfoRepository.findByDongNm(dongNm);
        List<CollectingBox> boxes = collectingBoxRepository.findAllByDongInfoAndTags(dongInfo, tags);
        return boxes.stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
