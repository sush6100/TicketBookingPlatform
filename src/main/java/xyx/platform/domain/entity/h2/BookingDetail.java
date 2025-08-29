package xyx.platform.domain.entity.h2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String phone;
    private Integer theatreId;
    private String bookedSeatIds;
    private String price;
    private String showDate;
    private String showTime;
    private String payRef;

}
