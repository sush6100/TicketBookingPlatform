package xyx.platform.domain.backup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Show")
public class Show {
    @Id
    private String id;
    private String city;
    private String area;
    private String theatre;
    private String language;
    private String genre;
    private Movie movie;
    private Audi audi;
}
