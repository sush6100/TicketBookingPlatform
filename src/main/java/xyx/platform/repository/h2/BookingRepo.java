package xyx.platform.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyx.platform.domain.entity.h2.BookingDetail;

@Repository
public interface BookingRepo extends JpaRepository<BookingDetail, Integer> {

}
