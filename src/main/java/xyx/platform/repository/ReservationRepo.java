package xyx.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyx.platform.domain.entity.SeatAvailability;

@Repository
public interface ReservationRepo extends JpaRepository<SeatAvailability, String> {

}
