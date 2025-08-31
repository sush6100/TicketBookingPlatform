package xyx.platform.domain.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "CouponDetail")
public class CouponDetail {
    @Id
    private String coupon;
    private Integer theatreId;
    private String offerUrl;
    private String validationUrl;
    private String urlParams;
}
