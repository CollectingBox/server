package contest.collectingbox.module.autocomplete.domain;

import contest.collectingbox.module.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoCompleteRepository extends JpaRepository<Location, Long>, AutoCompleteRepositoryCustom {

}
