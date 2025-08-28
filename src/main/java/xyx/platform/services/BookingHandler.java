package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyx.platform.domain.entity.BookingDetail;
import xyx.platform.repository.BookingRepo;
import xyx.platform.repository.TheatreRepo;

@Service
public class BookingHandler {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private TheatreRepo theatreRepo;

    public void book(BookingDetail booking) {
        //call payment api and get payment ref
        String payId = theatreRepo.findPayIdById(booking.getTheatreId());
        String payRef = payUsingPayid(payId);
        booking.setPayRef(payRef);
        bookingRepo.save(booking);
        //send notification bookingDetails.getEmail bookingDetails.getPhone
    }

    private String payUsingPayid(String payId) {
        return "payRef";
    }
}
