package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.backup.Seat;

public interface SeatRepo extends MongoRepository<Seat, String> {
//    List<Seat> findAllSeatsByCity(String city, List<String> statuses);

}
