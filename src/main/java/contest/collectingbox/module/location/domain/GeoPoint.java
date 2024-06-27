package contest.collectingbox.module.location.domain;

import contest.collectingbox.global.exception.CollectingBoxException;
import lombok.Getter;

import static contest.collectingbox.global.exception.ErrorCode.*;

@Getter
public class GeoPoint {
    private final double longitude;
    private final double latitude;

    public GeoPoint(Double longitude, Double latitude) {
        validate(longitude, latitude);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private void validate(Double longitude, Double latitude) {
        if (longitude == null) {
            throw new CollectingBoxException(NOT_NULL_LONGITUDE);
        }
        if (latitude == null) {
            throw new CollectingBoxException(NOT_NULL_LATITUDE);
        }
        if (invalidLongitude(longitude)) {
            throw new CollectingBoxException(INVALID_LONGITUDE);
        }
        if (invalidLatitude(latitude)) {
            throw new CollectingBoxException(INVALID_LATITUDE);
        }
    }

    private boolean invalidLongitude(double longitude) {
        return longitude < -180 || longitude > 180;
    }

    private boolean invalidLatitude(double latitude) {
        return latitude < -90 || latitude > 90;
    }
}
