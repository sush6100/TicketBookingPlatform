package xyx.platform.services;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;
import xyx.platform.domain.entity.mongo.Shows;
import xyx.platform.repository.mongo.MongoConfig;
import xyx.platform.repository.mongo.MovieRepo;
import xyx.platform.repository.mongo.ShowsRepo;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShowsHandler {
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ShowsRepo showsRepo;
    @Autowired
    private MongoConfig mongoConfig;

    @Value("${search.theatre}")
    private String searchTheatreQuery;

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public List<Shows> getMovieList(Map<String, String> prm) {

        final BasicQuery basicQuery = new BasicQuery(String.format(searchTheatreQuery, prm.get("movie"), prm.get("city"), prm.get("time")));
//        final List<Show> result = mongoConfig.getMongoTemplate().find(basicQuery, Show.class);
        final List<Shows> shows = mongoConfig.getMongoTemplate().find(basicQuery, Shows.class);

        log.info("All Movies: {} in: {}", shows, prm);

        return shows;
    }
}
