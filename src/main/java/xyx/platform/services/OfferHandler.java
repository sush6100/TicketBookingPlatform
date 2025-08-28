package xyx.platform.services;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyx.platform.repository.TheatreRepo;
import xyx.platform.resilience.Fallback;

import java.util.List;

@Slf4j
@Service
public class OfferHandler {

    @Autowired
    private TheatreRepo theatreRepo;
    @Autowired
    private Fallback fallback;

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public Double getOfferPrice(Integer theatreId, List<String> seatIds, String showTime, String coupon) {
        Double theatreOfferPrice = getTheatreOfferPrice(theatreId, seatIds, showTime, coupon);
        Double xyzOfferPrice = getXyzDiscount(theatreId, seatIds, showTime, coupon);
        return theatreOfferPrice + xyzOfferPrice;
    }
    private Double getTheatreOfferPrice(Integer theatreId, List<String> seatIds, String showTime, String coupon) {

        String promoUrl = theatreRepo.findTheatreOfferPromoUrlById(theatreId);
        //invoke promo url of theatre to get offer price
        log.info("The promo pricing details: {}", "TBD");

        return null;
    }
    private Double getXyzDiscount(Integer theatreId, List<String> seatIds, String showTime, String coupon) {

        log.info("The pricing details: {}", coupon);

        return null;
    }
    private List<String> fallback(String seatType, String promoCode, Throwable e) {

        return fallback.fallback(seatType, 0, e);
    }
}
