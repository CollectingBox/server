package contest.collectingbox.module.collectingbox.application;

import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.location.domain.Location;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

import static contest.collectingbox.module.collectingbox.domain.Tag.CLOTHES;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectingBoxServiceTest {

    private final double latitude = 37.4888953606578;
    private final double longitude = 126.901185398046;

    private Point center;

    @Value("${collecting-box.search.radius.meter}")
    private int radius;


    @InjectMocks
    private CollectingBoxService collectingBoxService;

    @Mock
    private CollectingBoxRepository collectingBoxRepository;

    @BeforeEach
    void setUp() {
        center = GeometryUtil.toPoint(longitude, latitude);
    }

    @Test
    @DisplayName("위도와 경도를 기준으로 특정 반경에 위치한 수거함 목록 조회 성공")
    void findCollectingBoxesWithinArea_Success_withinArea() {
        // given
        List<Tag> tags = List.of(CLOTHES);

        CollectingBox box = CollectingBox.builder()
                .id(1L)
                .location(Location.builder().point(GeometryUtil.toPoint(126.902174727201, 37.4871190365551)).build())
                .tag(CLOTHES)
                .build();

        // when
        when(collectingBoxRepository.findAllWithinArea(center, radius, tags)).thenReturn(Arrays.asList(box));

        List<CollectingBoxResponse> result =
                collectingBoxService.findCollectingBoxesWithinArea(latitude, longitude, tags);

        // then
        assertThat(result.get(0).getId()).isEqualTo(box.getId());
    }

    @Test
    @DisplayName("수거함 id로 수거함 상세 정보 조회")
    void findBoxDetail_Success_ById() {
        // given
        Long collectionId = 1L;
        CollectingBoxDetailResponse expectedResponse = CollectingBoxDetailResponse.builder()
                .roadName("roadName")
                .streetNumber("streetNumber")
                .modifiedDate("2024-04-12 00:00:00.000000")
                .tag("CLOTHES")
                .build();
        CollectingBox box = CollectingBox.builder().id(collectionId).build();

        // when
        when(collectingBoxRepository.findById(collectionId)).thenReturn(Optional.ofNullable(box));
        when(collectingBoxRepository.findDetailById(collectionId)).thenReturn(expectedResponse);
        CollectingBoxDetailResponse response = collectingBoxService.findBoxDetailById(collectionId);

        // then
        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response.getLocation()).isNull();
    }
}