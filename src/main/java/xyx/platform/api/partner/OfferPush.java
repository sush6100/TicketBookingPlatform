package xyx.platform.api.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyx.platform.domain.entity.mongo.CouponDetail;
import xyx.platform.repository.mongo.OfferRepo;

@RestController
@RequestMapping(value = "/push")
@Slf4j
public class OfferPush {

    @Autowired
    private OfferRepo offerRepo;

    @PostMapping(value = "/coupon")
    public ResponseEntity<CouponDetail> theatre(@RequestBody CouponDetail couponDetail) {


        log.info("Onboarding coupon: {}", couponDetail);

        return ResponseEntity.ok(offerRepo.save(couponDetail));
    }
}
