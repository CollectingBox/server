package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.Tag;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PublicDataApiInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sido;

    @Column(nullable = false)
    private String sigungu;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tag tag;

    @Column(nullable = false)
    private String callAddress;
}
