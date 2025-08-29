package xyx.platform.services;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;
import xyx.platform.repository.mongo.MongoConfig;
import xyx.platform.domain.entity.h2.SeatAvailability;
import xyx.platform.domain.entity.mongo.Seat;
import xyx.platform.domain.entity.mongo.Show;
import xyx.platform.repository.h2.ReservationRepo;
import xyx.platform.repository.h2.TheatreRepo;
import xyx.platform.repository.mongo.MovieRepo;
import xyx.platform.repository.mongo.SeatRepo;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SeatHandler {

    @Autowired
    private TheatreRepo theatreRepo;
    @Autowired
    private SeatRepo seatRepo;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ReservationRepo reservationRepo;
    @Autowired
    private MongoConfig mongoConfig;
    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public String isSeatAvailable(String show, String seatId) {

        if ("true".contentEquals(getAvailabilityFromDB(show, seatId)) && "true".contentEquals(getAvailabilityFromTheatre(show, seatId))) {
            log.info("The status of seat {} for show {} is {}", seatId, show, "");
            return "true";
        }

        log.info("The status of seat {} for show {} is {}", seatId, show, "");

        return "false";
    }

    private String getAvailabilityFromDB(String show, String seatId) {
        log.info("The status of seat {} for show {} is {}", seatId, show, "");
        return "";
    }

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    private String getAvailabilityFromTheatre(String show, String seatId) {
        log.info("The status of seat {} for show {} is {}", seatId, show, "");
        return "";
    }

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public String lockSeat(String theatre, String seatId, String showDate, String showTime) {

        //update local DB for lock
        //update theatre side seat lock
        SeatAvailability seatAvailability = new SeatAvailability();
        seatAvailability.setSeatId(createSeatId(theatre, seatId));
        seatAvailability.setDate(showDate);
        seatAvailability.setTime(showTime);
        seatAvailability.setIsAvailable(0);
        reservationRepo.save(seatAvailability);
        log.info("The status of seat {} for show {} is {}", seatId, showTime, "locked");

        return "locked";

    }

    private String createSeatId(String theatre, String seatId) {
        return theatre.concat("_").concat(seatId);
    }

    public List<Seat> getSeats() {
        MongoTemplate mongoTemplate = mongoConfig.getMongoTemplate();
        BasicQuery basicQuery = new BasicQuery("{ \"movie._id\": \"Jurassic Park Comedy\", \"city\": \"Bengaluru\" }");
        List<Show> result = mongoTemplate.find(basicQuery, Show.class);


        return result.get(0).getAudi().getSeats();
    }

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public String unLockSeat(String seat, String show) {

        //update local DB for lock
        //update theatre side seat lock
        log.info("The status of seat {} for show {} is {}", seat, show, "locked");

        return "unlocked";
    }

    public String lockSeatLocally(String seat, String show) {
        //update local DB for lock and set to true
        return "";
    }

    public String unLockSeatLocally(String seat, String show) {
        //update local DB for lock and set to false
        return "";
    }

    public Map<String, String> lockStatus(String theatre, String seat, String show) {
        return null;
    }

    public Map<String, String> getAllSeats(String theatre, String audi, String show) {
        return null;
    }
}
