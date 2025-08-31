package xyx.platform.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyx.platform.domain.entity.h2.Theatre;
@Repository
public interface TheatreRepo extends JpaRepository<Theatre, String> {
    String findPayIdById(Integer id);
}
