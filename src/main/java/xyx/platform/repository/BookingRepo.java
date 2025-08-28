package xyx.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyx.platform.domain.entity.BookingDetail;

@Repository
public interface BookingRepo extends JpaRepository<BookingDetail, Integer> {

}
