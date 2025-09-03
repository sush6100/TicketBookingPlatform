package xyx.platform.api.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyx.platform.domain.entity.mongo.TheatreShows;
import xyx.platform.services.ShowsHandler;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/shows")
@Slf4j
public class ShowsGetter {

    @Autowired
    private ShowsHandler showsHandler;

    @GetMapping(value = "/movie_list")
    public ResponseEntity<List<TheatreShows>> movieList(@RequestParam Map<String, String> params) {

        log.info("The temperature date details: {}", "seatHandler");

        return ResponseEntity.ok(showsHandler.getMovieList(params));
    }
}
