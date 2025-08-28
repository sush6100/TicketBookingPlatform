package xyx.platform.api.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyx.platform.services.SeatHandler;

import java.util.Map;

@RestController
@RequestMapping(value = "/handle_seat")
@Slf4j
public class HandleSeats {

    @Autowired
    private SeatHandler seatHandler;

    @GetMapping(value = "/lock_status")
    public ResponseEntity<Map<String, String>> lockStatus(@RequestParam String theatre, @RequestParam String audi, @RequestParam String show) {

        Map<String, String> lockStatus = seatHandler.lockStatus(theatre, audi, show);
        log.info("Lock details: {} for show: {}", lockStatus, show);

        return ResponseEntity.ok(lockStatus);
    }
    @GetMapping(value = "/all_seats")
    public ResponseEntity<Map<String, String>> allSeats(@RequestParam String theatre, @RequestParam String audi, @RequestParam String show) {

        Map<String, String> lockStatus = seatHandler.getAllSeats(theatre, audi, show);
        log.info("Lock details: {} for show: {}", lockStatus, show);

        return ResponseEntity.ok(lockStatus);
    }
    @PostMapping(value = "/lock")
    public ResponseEntity<Boolean> reserveSeat(@RequestParam String theatre, @RequestParam String seatId, @RequestParam String showDate, @RequestParam String showTime) {

        seatHandler.lockSeat(theatre, seatId, showDate, showTime);
        log.info("Seat Reserved: {} for show time: {}", seatId, showTime);

        return ResponseEntity.ok(false);
    }
    @GetMapping(value = "/release")
    public ResponseEntity<Boolean> releaseSeat(@RequestParam String theatre, @RequestParam String seat, @RequestParam String showTime) {

        seatHandler.unLockSeat(seat, showTime);
        log.info("Seat Released: {} for show: {}", seat, showTime);

        return ResponseEntity.ok(false);
    }
}
