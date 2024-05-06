package contest.collectingbox.module.autocomplete.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import contest.collectingbox.module.autocomplete.dto.AddressDto;
import contest.collectingbox.module.autocomplete.dto.QAddressDto;
import contest.collectingbox.module.location.domain.QLocation;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AutoCompleteRepositoryImpl implements AutoCompleteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AutoCompleteRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AddressDto> findAutoComplete(String query) {

        QLocation location = QLocation.location;
        // SELECT DISTINCT l FROM Location l WHERE l.address.streetNum LIKE %:query% ORDER BY l.address.sigungu, l.address.dong LIMIT 5
        return queryFactory
                .selectDistinct(new QAddressDto(location.dongInfo.sigunguNm, location.dongInfo.dongNm))
                .from(location)
                .where(location.streetNum.contains(query))
                .orderBy(location.dongInfo.sigunguNm.asc(), location.dongInfo.dongNm.asc())
                .limit(5)
                .fetch();
    }
}
