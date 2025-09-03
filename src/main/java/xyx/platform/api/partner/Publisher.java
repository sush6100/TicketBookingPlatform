package xyx.platform.api.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyx.platform.domain.entity.mongo.TheatreShows;
import xyx.platform.services.PublishHandler;

@RestController
@RequestMapping(value = "/publish")
@Slf4j
public class Publisher {

    @Autowired
    private PublishHandler publishHandler;
    @PostMapping(value = "/shows")
    public ResponseEntity<TheatreShows> publishShows(@RequestBody TheatreShows theatreShows) {

        final TheatreShows retTheatreShows = publishHandler.publishShows(theatreShows);
        log.info("The temperature date details: {}", retTheatreShows);

        return ResponseEntity.ok(retTheatreShows);
    }
}
