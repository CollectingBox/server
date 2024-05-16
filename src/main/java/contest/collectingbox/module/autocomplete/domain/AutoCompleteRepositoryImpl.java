package contest.collectingbox.module.autocomplete.domain;

import static contest.collectingbox.module.location.domain.QDongInfo.dongInfo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import contest.collectingbox.module.autocomplete.dto.AddressDto;
import contest.collectingbox.module.autocomplete.dto.QAddressDto;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AutoCompleteRepositoryImpl implements AutoCompleteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AutoCompleteRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AddressDto> getAutoComplete(String query) {
        return queryFactory
                .selectDistinct(new QAddressDto(dongInfo.sigunguNm, dongInfo.dongNm))
                .from(dongInfo)
                .where(containsQuery(query))
                .orderBy(dongInfo.sigunguNm.asc(), dongInfo.dongNm.asc())
                .limit(5)
                .fetch();
    }

    private BooleanExpression containsQuery(String query) {
        String[] split = query.split(" ");
        if (split.length == 1) {
            return dongInfo.sigunguNm.contains(query)
                    .or(dongInfo.dongNm.contains(query));
        }
        System.out.println(2);
        return dongInfo.sigunguNm.contains(split[0])
                .and(dongInfo.dongNm.contains(split[split.length-1]));

    }
}
