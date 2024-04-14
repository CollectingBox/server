package contest.collectingbox.global.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GeometryUtil {

    private static final int SRID = 4326;

    public static Point toPoint(double longitude, double latitude) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), SRID);
        return geometryFactory.createPoint(new Coordinate(longitude, latitude));
    }
}
