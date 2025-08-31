package xyx.platform.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyx.platform.domain.entity.mongo.CouponDetail;

public interface OfferRepo extends MongoRepository<CouponDetail, String> {

}
