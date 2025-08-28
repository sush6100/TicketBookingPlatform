package xyx.platform.api.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyx.platform.services.OfferHandler;

import java.util.List;

@RestController
@RequestMapping(value = "/manage_offer")
@Slf4j
public class OfferManagement {

    @Autowired
    private OfferHandler offerHandler;

    @GetMapping(value = "/apply_offer")
    public ResponseEntity<Double> calculateOffer(@RequestParam Integer theatreId, @RequestParam List<String> seatIds, @RequestParam String showTime, @RequestParam String coupon) {

        offerHandler.getOfferPrice(theatreId, seatIds, showTime, coupon);

        log.info("The temperature date details: {}", "seatHandler");

        return ResponseEntity.ok(0.0);
    }
}
