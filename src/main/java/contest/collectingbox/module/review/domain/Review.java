package contest.collectingbox.module.review.domain;

import contest.collectingbox.global.common.BaseTimeEntity;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="collecting_box_id")
    @NotNull
    private CollectingBox collectingBox;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

}
