package contest.collectingbox.module.location.domain;

import contest.collectingbox.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dong_cd")
    private DongInfo dongInfo;

    private String name;
    private String roadName;
    private String streetNum;

    @Column(columnDefinition = "point")
    private Point point;

    public double longitude() {
        return point.getX();
    }

    public double latitude() {
        return point.getY();
    }
}
