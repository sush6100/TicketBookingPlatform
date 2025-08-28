package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyx.platform.domain.dtos.TheatreDto;
import xyx.platform.domain.entity.h2.Theatre;
import xyx.platform.repository.h2.TheatreRepo;

@Service
public class TheatreOnboarder {

    @Autowired
    private TheatreRepo theatreRepo;
    public TheatreDto onboard(TheatreDto theatreDto) {
        Theatre theatre = new Theatre();
        theatre.setCity(theatreDto.getCity());
        theatre.setName(theatreDto.getName());
        theatre.setTheatreOfferPromoUrl(theatreDto.getTheatreOfferPromoUrl());
        theatre.setLockUrl(theatreDto.getLockUrl());
        theatre.setPayId(theatreDto.getPayId());
        theatre.setPricingUrl(theatreDto.getPricingUrl());
        theatre.setUnLockUrl(theatreDto.getUnLockUrl());
        theatreRepo.save(theatre);
        theatreDto.setId(theatre.getId());
        return theatreDto;
    }
}
