package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyx.platform.domain.entity.h2.BookingDetail;
import xyx.platform.repository.h2.BookingRepo;
import xyx.platform.repository.h2.TheatreRepo;

@Service
public class BookingHandler {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private TheatreRepo theatreRepo;

    @Transactional
    public BookingDetail book(BookingDetail booking) {
        //call payU payment api and get payment ref after payment is successful.
        Object payId = theatreRepo.findPayDetailByTheatreId(booking.getTheatreId());
        String payRef = payUsingPayid(payId.toString());
        booking.setPayRef(payRef);
        BookingDetail bookingDetail = bookingRepo.save(booking);
        //send notification bookingDetails.getEmail bookingDetails.getPhone
        return bookingDetail;
    }

    private String payUsingPayid(String payId) {
        return "payRef";
    }
}
