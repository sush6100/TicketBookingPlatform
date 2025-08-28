package xyx.platform.api.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyx.platform.domain.backup.Show;
import xyx.platform.domain.backup.Shows;
import xyx.platform.services.PublisheHandler;

import java.util.List;

@RestController
@RequestMapping(value = "/publish")
@Slf4j
public class Publisher {

    @Autowired
    private PublisheHandler publisheHandler;

    @PostMapping(value = "/movie")
    public ResponseEntity<List<Show>> publishMovie(@RequestBody Shows shows) {

        final List<Show> retShows = publisheHandler.publish(shows.getShows());
        log.info("The temperature date details: {}", retShows);

        return ResponseEntity.ok(retShows);
    }
}
