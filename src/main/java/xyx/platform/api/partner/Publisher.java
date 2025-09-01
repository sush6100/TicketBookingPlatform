package xyx.platform.api.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyx.platform.domain.entity.mongo.Show;
import xyx.platform.domain.entity.mongo.Shows;
import xyx.platform.services.PublishHandler;

import java.util.List;

@RestController
@RequestMapping(value = "/publish")
@Slf4j
public class Publisher {

    @Autowired
    private PublishHandler publishHandler;

    @PostMapping(value = "/movie")
    public ResponseEntity<List<Show>> publishMovie(@RequestBody Shows shows) {

        final List<Show> retShows = publishHandler.publish(shows.getShows());
        log.info("The temperature date details: {}", retShows);

        return ResponseEntity.ok(retShows);
    }
}
