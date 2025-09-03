package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyx.platform.domain.entity.mongo.TheatreShows;
import xyx.platform.repository.mongo.TheatreShowsRepo;

@Service
public class PublishHandler {

    @Autowired
    private TheatreShowsRepo theatreShowsRepo;
    @Transactional
    public TheatreShows publishShows(TheatreShows theatreShows) {

        return theatreShowsRepo.save(theatreShows);
    }
}
