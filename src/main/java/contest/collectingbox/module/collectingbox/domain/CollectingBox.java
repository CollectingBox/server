package contest.collectingbox.module.collectingbox.domain;

import contest.collectingbox.global.common.BaseTimeEntity;
import contest.collectingbox.module.location.domain.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collecting_box")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectingBox extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @NotNull
    private Location location;

    @Enumerated(value=EnumType.STRING)
    @NotNull
    private Tag tag;


}
