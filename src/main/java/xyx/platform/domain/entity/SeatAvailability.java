package xyx.platform.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatAvailability {
    @Id
    private String seatId;
    private String date;
    private String time;
    private int isAvailable;
}
