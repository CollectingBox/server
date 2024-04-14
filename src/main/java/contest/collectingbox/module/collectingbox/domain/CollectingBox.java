package contest.collectingbox.module.collectingbox.domain;

import contest.collectingbox.global.common.BaseTimeEntity;
import contest.collectingbox.module.location.domain.Location;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectingBox extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Tag tag;
}
