package contest.collectingbox.module.autocomplete.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import contest.collectingbox.module.autocomplete.dto.AddressDto;
import contest.collectingbox.module.autocomplete.dto.QAddressDto;
import contest.collectingbox.module.location.domain.QLocation;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AutoCompleteRepositoryImpl implements AutoCompleteRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public AutoCompleteRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AddressDto> findAutoComplete(String query) {

        QLocation location = QLocation.location;
        // SELECT DISTINCT l FROM Location l WHERE l.address.streetNum LIKE %:query% ORDER BY l.address.sigungu, l.address.dong LIMIT 5
        return queryFactory
                .select(new QAddressDto(location.address.sigungu, location.address.dong))
                .from(location)
                .where(location.address.streetNum.contains(query))
                .orderBy(location.address.sigungu.asc(), location.address.dong.asc())
                .limit(5)
                .fetch();
    }
}
