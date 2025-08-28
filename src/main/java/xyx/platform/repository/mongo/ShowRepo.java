package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.entity.mongo.Show;

public interface ShowRepo extends MongoRepository<Show, String> {
}
