package xyx.platform.services;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyx.platform.repository.h2.TheatreRepo;
import xyx.platform.resilience.Fallback;

import java.util.List;

@Slf4j
@Service
public class PricingHandler {

    @Autowired
    private TheatreRepo theatreRepo;
    @Autowired
    private Fallback fallback;

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public Double getSeatPrice(Integer theatreId, String seatId, String coupon) {

        log.info("The pricing details: {}", "TBD");

        return null;
    }
    private List<String> fallback(String seatType, String promoCode, Throwable e) {

        return fallback.fallback(seatType, 0, e);
    }
}
