package contest.collectingbox.module.location.domain;

import contest.collectingbox.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class GeoPoint {
    private final double longitude;
    private final double latitude;

    public GeoPoint(double longitude, double latitude) {
        if (invalidLongitude(longitude) || invalidLatitude(latitude)) {
            throw new IllegalArgumentException("Invalid parameter value for longitude or latitude");
        }
        this.longitude = longitude;
        this.latitude = latitude;
    }

    private boolean invalidLongitude(double longitude) {
        return longitude < -180 || longitude > 180;
    }

    private boolean invalidLatitude(double latitude) {
        return latitude < -90 || latitude > 90;
    }
}
