package xyx.platform.domain.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Movie")
public class Movie {
    @Id
    private String id;
    private String name;
    private String  showTimings;
    private String  endDate;
    private String  startDate;
}
