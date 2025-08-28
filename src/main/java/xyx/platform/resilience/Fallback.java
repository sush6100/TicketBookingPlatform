package xyx.platform.resilience;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Fallback {

    public List<String> fallback(String seatType, Integer days, Throwable e) {
        try {
            log.info("Failed");

            //The below is only dummy details which are to be fetched from the offline json files which are received from the website

            return Arrays.asList("Fail");
        } catch (Throwable th) {
            throw new RuntimeException(th.getMessage());
        }
    }
}
