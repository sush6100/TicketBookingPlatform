package xyx.platform.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyx.platform.domain.entity.h2.Theatre;
@Repository
public interface TheatreRepo extends JpaRepository<Theatre, String> {
    @Query("select payDetail from Theatre where theatreId = :theatreId")
    String findPayDetailByTheatreId(@Param("theatreId") Integer id);
}
