package xyx.platform.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SeatHandler {
    public Map<String, String> lockStatus(String theatre, String audi, String show) {
        return null;
    }

    public Map<String, String> getAllSeats(String theatre, String audi, String show) {
        return null;
    }

    public void lockSeat(String theatre, String seatId, String showDate, String showTime) {

    }

    public void unLockSeat(String seat, String show) {

    }
}
