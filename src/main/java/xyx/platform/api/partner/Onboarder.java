package xyx.platform.api.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyx.platform.domain.dtos.TheatreDto;
import xyx.platform.services.TheatreOnboarder;

@RestController
@RequestMapping(value = "/onboard")
@Slf4j
public class Onboarder {

    @Autowired
    private TheatreOnboarder theatreOnboarder;

    @PostMapping(value = "/theatre")
    public ResponseEntity<TheatreDto> theatre(@RequestBody TheatreDto theatreDto) {


        log.info("Onboarded theatre: {}", theatreDto);

        return ResponseEntity.ok(theatreOnboarder.onboard(theatreDto));
    }
}
