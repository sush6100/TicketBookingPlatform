package xyx.platform.api.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyx.platform.domain.entity.h2.BookingDetail;
import xyx.platform.services.BookingHandler;

@RestController
@RequestMapping(value = "/book")
@Slf4j
public class Booking {

    @Autowired
    private BookingHandler bookingHandler;
    @PostMapping(value = "/ticket")
    public ResponseEntity<BookingDetail> movie(@RequestBody BookingDetail booking) {

        log.info("Booking details: Seat: {}", booking.getBookedSeatIds());

        return ResponseEntity.ok(bookingHandler.book(booking));
    }
}
