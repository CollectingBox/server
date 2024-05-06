package contest.collectingbox.module.autocomplete.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import contest.collectingbox.module.autocomplete.dto.AddressDto;
import contest.collectingbox.module.autocomplete.dto.QAddressDto;
import contest.collectingbox.module.location.domain.QDongInfo;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AutoCompleteRepositoryImpl implements AutoCompleteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AutoCompleteRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AddressDto> findAutoComplete(String query) {
        QDongInfo dongInfo = QDongInfo.dongInfo;
        return queryFactory
                .selectDistinct(new QAddressDto(dongInfo.sigunguNm, dongInfo.dongNm))
                .from(dongInfo)
                .where(dongInfo.sigunguNm.contains(query).or(dongInfo.dongNm.contains(query)))
                .orderBy(dongInfo.sigunguNm.asc(), dongInfo.dongNm.asc())
                .limit(5)
                .fetch();
    }
}
