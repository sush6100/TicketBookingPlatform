package xyx.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyx.platform.domain.entity.Theatre;
@Repository
public interface TheatreRepo extends JpaRepository<Theatre, String> {
    String findPayIdById(Integer id);
    String findTheatreOfferPromoUrlById(Integer id);
}
