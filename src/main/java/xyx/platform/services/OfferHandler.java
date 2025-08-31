package xyx.platform.services;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xyx.platform.domain.entity.mongo.CouponDetail;
import xyx.platform.repository.h2.TheatreRepo;
import xyx.platform.repository.mongo.OfferRepo;
import xyx.platform.resilience.Fallback;

import java.util.List;

@Slf4j
@Service
public class OfferHandler {

    @Autowired
    private TheatreRepo theatreRepo;
    @Autowired
    private OfferRepo offerRepo;
    @Autowired
    private Fallback fallback;

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public String validateCoupon(Integer theatreId, List<String> seatIds, String showTime, String coupon) {
        CouponDetail couponDetail = offerRepo.findById(coupon).get();
        String url = couponDetail.getValidationUrl();
        url = formatUrl(theatreId, seatIds, showTime, couponDetail, url);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @Retry(name = "fetchRetry", fallbackMethod = "fallback")
    public Double applyCouponAndGetDiscount(Integer theatreId, List<String> seatIds, String showTime, String coupon, Double price) {
        CouponDetail couponDetail = offerRepo.findById(coupon).get();
        String url = couponDetail.getOfferUrl();
        url = formatUrl(theatreId, seatIds, showTime, couponDetail, url);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Double.class);
    }

    private String formatUrl(Integer theatreId, List<String> seatIds, String showTime, CouponDetail couponDetail, String url) {
        String params = couponDetail.getUrlParams();
        params = params.concat("?");
        if(params.contains("theatreId")) url = url.concat("theatreId=").concat(String.valueOf(theatreId));
        if(params.contains("showTime")) url = url.concat("showTime=").concat(showTime);
        if(params.contains("seatIds")) url = url.concat("seatIds=").concat(String.valueOf(seatIds));
        return url;
    }
    private List<String> fallback(String seatType, String promoCode, Throwable e) {

        return fallback.fallback(seatType, 0, e);
    }
}
