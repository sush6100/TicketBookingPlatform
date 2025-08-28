package xyx.platform.domain.backup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Seat")
public class Seat {
    private static final String SEPARATOR = "_";
    @Id
    private String seatId;
    private String type;
    private String row;
    private String col;

    public String getSeatId()
    {
        return getRow().concat(SEPARATOR).concat(getCol()).concat(SEPARATOR).concat(getType());
    }

}
