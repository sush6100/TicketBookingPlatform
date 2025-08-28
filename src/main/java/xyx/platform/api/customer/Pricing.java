package xyx.platform.api.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyx.platform.services.PricingHandler;

@RestController
@RequestMapping(value = "/pricing")
@Slf4j
public class Pricing {

    @Autowired
    private PricingHandler pricingHandler;

    @GetMapping(value = "/seat_price")
    public ResponseEntity<Double> seatPrice(@RequestParam Integer theatreId, @RequestParam String seatId, @RequestParam String showTime, @RequestParam String coupon) {

        pricingHandler.getSeatPrice(theatreId, seatId,  coupon);

        log.info("The temperature date details: {}", "seatHandler");

        return ResponseEntity.ok(0.0);
    }
}
