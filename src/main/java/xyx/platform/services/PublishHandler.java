package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyx.platform.domain.entity.mongo.Shows;
import xyx.platform.repository.mongo.ShowsRepo;

@Service
public class PublishHandler {

    @Autowired
    private ShowsRepo showsRepo;
    @Transactional
    public Shows publishShows(Shows shows) {

        return showsRepo.save(shows);
    }
}
