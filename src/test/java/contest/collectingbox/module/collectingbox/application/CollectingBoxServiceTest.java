package contest.collectingbox.module.collectingbox.application;

import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.Tags;
import contest.collectingbox.module.collectingbox.domain.repository.CollectingBoxRepository;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.location.domain.GeoPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static contest.collectingbox.global.exception.ErrorCode.NOT_FOUND_COLLECTING_BOX;
import static contest.collectingbox.module.collectingbox.domain.Tag.CLOTHES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollectingBoxServiceTest {

    @Value("${collecting-box.search.radius.meter}")
    private int radius;

    @InjectMocks
    private CollectingBoxService collectingBoxService;

    @Mock
    private CollectingBoxRepository collectingBoxRepository;

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

    @Nested
    class FindCollectingBoxesWithinArea {

        @Test
        @DisplayName("위도와 경도를 기준으로 특정 반경에 위치한 수거함 목록 조회에 성공한다.")
        void success_byWithinArea() {
            // given
            GeoPoint requestCenterPoint = new GeoPoint(126.901185398046, 37.4888953606578);
            Tags requestTags = new Tags(List.of(CLOTHES));

            CollectingBoxResponse response = CollectingBoxResponse.builder()
                    .id(1L)
                    .longitude(126.902174727201)
                    .latitude(37.4871190365551)
                    .tag(CLOTHES)
                    .build();

            when(collectingBoxRepository.findAllWithinArea(requestCenterPoint, radius, requestTags))
                    .thenReturn(List.of(response));

            // when
            List<CollectingBoxResponse> result =
                    collectingBoxService.findCollectingBoxesWithinArea(requestCenterPoint, requestTags);

            // then
            assertThat(result).containsExactly(response);
        }

    }

}