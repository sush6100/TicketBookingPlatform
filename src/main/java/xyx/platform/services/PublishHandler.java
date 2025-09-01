package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyx.platform.repository.mongo.MongoConfig;
import xyx.platform.domain.entity.mongo.Show;
import xyx.platform.repository.mongo.MovieRepo;
import xyx.platform.repository.mongo.ShowRepo;

import java.util.List;

@Service
public class PublishHandler {

    @Autowired
    private MongoConfig mongoConfig;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ShowRepo showRepo;
    @Transactional
    public List<Show> publish(List<Show> shows) {

        return showRepo.saveAll(shows);
    }
}
