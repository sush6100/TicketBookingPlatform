package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.entity.mongo.Shows;

public interface ShowsRepo extends MongoRepository<Shows, String> {
}
