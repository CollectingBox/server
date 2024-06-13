package contest.collectingbox.module.collectingbox.application;

import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.global.exception.ErrorCode;
import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.domain.Tags;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.location.domain.Location;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Point;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;
import java.util.List;

import static contest.collectingbox.global.exception.ErrorCode.NOT_FOUND_COLLECTING_BOX;
import static contest.collectingbox.module.collectingbox.domain.Tag.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectingBoxServiceTest {

    private Point center;

    @Value("${collecting-box.search.radius.meter}")
    private int radius;

    @InjectMocks
    private CollectingBoxService collectingBoxService;

    @Mock
    private CollectingBoxRepository collectingBoxRepository;

    @BeforeEach
    void setUp() {
        center = GeometryUtil.toPoint(126.901185398046, 37.4888953606578);
    }

    @Test
    @DisplayName("위도와 경도를 기준으로 특정 반경에 위치한 수거함 목록 조회 성공")
    void findCollectingBoxesWithinArea_Success_withinArea() {
        // given
        Tags tags = new Tags(List.of(CLOTHES, LAMP_BATTERY, MEDICINE, TRASH));

        CollectingBox box = CollectingBox.builder()
                .id(1L)
                .location(Location.builder().point(GeometryUtil.toPoint(126.902174727201, 37.4871190365551)).build())
                .tag(CLOTHES)
                .build();

        // when
        when(collectingBoxRepository.findAllWithinArea(center, radius, tags.toUnmodifiableList())).thenReturn(
                Collections.singletonList(box));

        List<CollectingBoxResponse> result =
                collectingBoxService.findCollectingBoxesWithinArea(center, tags);

        // then
        assertThat(result.get(0).getId()).isEqualTo(box.getId());
    }

    @Test
    @DisplayName("요청 파라미터로 넘어온 태그가 비어 있는 경우 예외 발생")
    void findCollectingBoxesWithinArea_Fail_ByTagIsEmpty() {
        // when, then
        Assertions.assertThatThrownBy(
                        () -> collectingBoxService.findCollectingBoxesWithinArea(center, new Tags(List.of())))
                .isInstanceOf(CollectingBoxException.class)
                .hasMessageContaining(ErrorCode.NOT_SELECTED_TAG.getMessage());
    }

    @Test
    @DisplayName("수거함 id로 수거함 상세 정보 조회")
    void findBoxDetail_Success_ById() {
        // given
        Long collectionId = 1L;
        CollectingBoxDetailResponse expectedResponse = CollectingBoxDetailResponse.builder()
                .point(GeometryUtil.toPoint(127.046374536307, 37.5067486779393))
                .roadName("roadName")
                .streetNumber("streetNumber")
                .modifiedDate("2024-04-12 00:00:00.000000")
                .tag(CLOTHES)
                .build();

        // when
        when(collectingBoxRepository.findDetailById(collectionId)).thenReturn(expectedResponse);
        CollectingBoxDetailResponse response = collectingBoxService.findBoxDetailById(collectionId);

        // then
        assertThat(response).isEqualTo(expectedResponse);
        assertThat(response.getLocation()).isNull();
    }

    @Test
    @DisplayName("수거함 조회 시 id가 존재하지 않을 때 예외 발생")
    void findBoxDetail_Fail_WhenBoxIdNotFound() {
        // given
        Long id = 100L;

        // when
        when(collectingBoxRepository.findDetailById(id))
                .thenThrow(new CollectingBoxException(NOT_FOUND_COLLECTING_BOX));
        // then
        assertThatThrownBy(() -> collectingBoxService.findBoxDetailById(id))
                .isInstanceOf(CollectingBoxException.class)
                .hasMessageContaining(NOT_FOUND_COLLECTING_BOX.getMessage());
    }

}