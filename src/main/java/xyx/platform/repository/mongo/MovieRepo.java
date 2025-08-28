package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.backup.Movie;

public interface MovieRepo extends MongoRepository<Movie, String> {
}
