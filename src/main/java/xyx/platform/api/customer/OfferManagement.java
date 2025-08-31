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
    @GetMapping(value = "/apply_coupon")
    public ResponseEntity<Double> applyCouponAndCalculateOffer(@RequestParam Integer theatreId, @RequestParam List<String> seatIds, @RequestParam String showTime, @RequestParam String coupon, @RequestParam Double price) {

        final Double priceAfterOffer = offerHandler.applyCouponAndGetDiscount(theatreId, seatIds, showTime, coupon, price);

        log.info("Offer Price: {}", priceAfterOffer);

        return ResponseEntity.ok(priceAfterOffer);
    }
    @GetMapping(value = "/validate_coupon")
    public ResponseEntity<String> validateCoupon(@RequestParam Integer theatreId, @RequestParam List<String> seatIds, @RequestParam String showTime, @RequestParam String coupon) {

        String isValid = offerHandler.validateCoupon(theatreId, seatIds, showTime, coupon);

        log.info("Is coupon {} valid: {}", coupon, isValid);

        return ResponseEntity.ok(isValid);
    }
}
