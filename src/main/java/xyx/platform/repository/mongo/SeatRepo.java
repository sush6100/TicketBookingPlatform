package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.entity.mongo.Seat;

public interface SeatRepo extends MongoRepository<Seat, String> {

}
