package xyx.platform.services;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;
import xyx.platform.domain.entity.mongo.TheatreShows;
import xyx.platform.repository.mongo.MongoConfig;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShowsHandler {
    private MongoConfig mongoConfig;

    public ShowsHandler(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    @Value("${search.theatre}")
    private String searchTheatreQuery;

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public List<TheatreShows> getMovieList(Map<String, String> prm) {

        final BasicQuery basicQuery = new BasicQuery(String.format(searchTheatreQuery, prm.get("movie"), prm.get("city"), prm.get("time")));
        final List<TheatreShows> shows = mongoConfig.getMongoTemplate().find(basicQuery, TheatreShows.class);

        log.info("All Movies: {} in: {}", shows, prm);

        return shows;
    }
}
