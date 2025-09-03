package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.entity.mongo.TheatreShows;

public interface TheatreShowsRepo extends MongoRepository<TheatreShows, String> {
}
