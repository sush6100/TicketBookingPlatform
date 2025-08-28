package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyx.platform.MongoConfig;
import xyx.platform.domain.entity.mongo.Show;
import xyx.platform.repository.mongo.MovieRepo;
import xyx.platform.repository.mongo.ShowRepo;

import java.util.List;

@Service
public class PublisheHandler {

    @Autowired
    private MongoConfig mongoConfig;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ShowRepo showRepo;
    public List<Show> publish(List<Show> shows) {

        return showRepo.saveAll(shows);
    }
}
