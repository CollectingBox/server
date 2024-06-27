package contest.collectingbox.module.location.domain;

import contest.collectingbox.global.exception.CollectingBoxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class GeoPointTest {

    @Test
    @DisplayName("경도가 -180보다 작은 경우 실패한다.")
    void fail_byLongitudeLessThanMin() {
        // when, then
        assertThatThrownBy(() -> new GeoPoint(-202.902174727201, 37.4871190365551))
                .isInstanceOf(CollectingBoxException.class);
    }

    @Test
    @DisplayName("경도가 180보다 큰 경우 실패한다.")
    void fail_byLongitudeGreaterThanMax() {
        // when, then
        assertThatThrownBy(() -> new GeoPoint(202.902174727201, 37.4871190365551))
                .isInstanceOf(CollectingBoxException.class);
    }

    @Test
    @DisplayName("위도가 -90보다 작은 경우 실패한다.")
    void fail_byLatitudeLessThanMin() {
        // when, then
        assertThatThrownBy(() -> new GeoPoint(126.902174727201, -95.4871190365551))
                .isInstanceOf(CollectingBoxException.class);
    }

    @Test
    @DisplayName("위도가 90보다 큰 경우 실패한다.")
    void fail_byLatitudeGreaterThanMax() {
        // when, then
        assertThatThrownBy(() -> new GeoPoint(126.902174727201, 95.4871190365551))
                .isInstanceOf(CollectingBoxException.class);
    }
}