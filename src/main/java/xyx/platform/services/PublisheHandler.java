package xyx.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyx.platform.MongoConfig;
import xyx.platform.domain.backup.Show;
import xyx.platform.repository.mongo.MovieRepo;
import xyx.platform.repository.mongo.ShowRepo;

import java.util.List;

@Service
public class PublisheHandler {

    @Autowired
    private MongoConfig mongoConfig;
    @Autowired
    private MovieRepo movieRepo;
    @Autowired
    private ShowRepo showRepo;
    public List<Show> publish(List<Show> shows) {

        /*xyx.platform.domain.backup.Show show = new xyx.platform.domain.backup.Show();
        show.setCity("Bengaluru");
        show.setLanguage("English");
        show.setGenre("Drama");
        Movie movie = new Movie("Jurassic Park Drama", "12-15;16-19;20-23", "26-09-2025", "26-10-2025");
        show.setMovie(movie);
        Audi audi = new Audi("1", Arrays.asList(new Seat("1-1-Reclinier", "Reclienir", "1", "1"), new Seat("2-1-Reclinier", "Middle", "2", "1")));
        show.setAudi(audi);

        xyx.platform.domain.backup.Show show1 = new xyx.platform.domain.backup.Show();
        show1.setCity("Bengaluru");
        show1.setLanguage("English");
        show1.setGenre("Comedy");
        Movie movie1 = new Movie("Jurassic Park Comedy", "12-15;16-19;20-23", "26-09-2025", "26-10-2025");
        show1.setMovie(movie1);
        Audi audi1 = new Audi("2", Arrays.asList(new Seat("1-1-Reclinier", "Reclienir", "1", "1"), new Seat("2-1-Reclinier", "Middle", "2", "1")));
        show1.setAudi(audi1);*/

        return showRepo.saveAll(shows);
    }
}
