package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.entity.mongo.Movie;

public interface MovieRepo extends MongoRepository<Movie, String> {
}
