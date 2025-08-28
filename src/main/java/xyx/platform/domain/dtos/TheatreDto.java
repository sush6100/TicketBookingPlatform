package xyx.platform.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreDto {

    private Integer id;
    private String name;
    private String city;
    private String payId;
    private String lockUrl;
    private String unLockUrl;
    private String pricingUrl;
    private String theatreOfferPromoUrl;

}
