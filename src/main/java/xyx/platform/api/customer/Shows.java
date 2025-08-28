package xyx.platform.api.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyx.platform.domain.entity.mongo.Show;
import xyx.platform.services.ShowsHandler;

import java.util.List;

@RestController
@RequestMapping(value = "/shows")
@Slf4j
public class Shows {

    @Autowired
    private ShowsHandler showsHandler;

    @GetMapping(value = "/movie_list")
    public ResponseEntity<List<Show>> movieList(@RequestParam String city) {


        log.info("The temperature date details: {}", "seatHandler");

        return ResponseEntity.ok(showsHandler.getMovieList(city));
    }

    @GetMapping(value = "/movie_detail")
    public ResponseEntity<List<String>> movieDetail(@RequestParam String location, @RequestParam Integer dayRange) {

//        final List<TemperatureDate> temperatureDates = reportReaderService.readReportWithDayRange(location, dayRange);
//        log.info("The temperature date details: {}", temperatureDates);

        return ResponseEntity.ok(null);
    }
}
