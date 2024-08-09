package contest.collectingbox.module.collectingbox.application;

import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.Tags;
import contest.collectingbox.module.collectingbox.domain.repository.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.location.domain.DongInfo;
import contest.collectingbox.module.location.domain.repository.DongInfoRepository;
import contest.collectingbox.module.location.domain.GeoPoint;
import lombok.RequiredArgsConstructor;
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
    public List<CollectingBoxResponse> findCollectingBoxesWithinArea(final GeoPoint center, final Tags tags) {
        return collectingBoxRepository.findAllWithinArea(center, radius, tags);
    }

    @Transactional(readOnly = true)
    public CollectingBoxDetailResponse findBoxDetailById(Long collectionId) {
        return collectingBoxRepository.findDetailById(collectionId);
    }

    @Transactional(readOnly = true)
    public List<CollectingBoxResponse> searchCollectingBoxes(final String query, final Tags tags) {
        // '강남구'
        if (query.endsWith("구")) {
            return searchBySigunguNm(query, tags);
        }

        String[] splitQuery = query.split(" ");

        // '역삼1동'
        if (splitQuery.length == 1) {
            return searchByDongNm(query, tags);
        }

        // '강남구 역삼1동'
        return searchByDongNm(splitQuery[1], tags);
    }

    private List<CollectingBoxResponse> searchBySigunguNm(String query, Tags tags) {
        return dongInfoRepository.findAllBySigunguNm(query).stream()
                .flatMap(dongInfo ->
                        collectingBoxRepository.findAllByDongInfoAndTags(dongInfo, tags.getTags()).stream())
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }

    private List<CollectingBoxResponse> searchByDongNm(String dongNm, Tags tags) {
        DongInfo dongInfo = dongInfoRepository.findByDongNm(dongNm);
        List<CollectingBox> boxes = collectingBoxRepository.findAllByDongInfoAndTags(dongInfo, tags.getTags());
        return boxes.stream()
                .map(CollectingBoxResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
