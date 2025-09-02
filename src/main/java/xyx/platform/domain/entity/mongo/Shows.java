package xyx.platform.domain.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Theatre")
public class Shows {
    @Id
    private String theatreId;
    private String area;
    private String city;
    private List<Show> shows;
}
