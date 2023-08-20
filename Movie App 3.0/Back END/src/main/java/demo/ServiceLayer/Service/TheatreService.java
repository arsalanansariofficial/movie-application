package demo.ServiceLayer.Service;

import demo.DatabaseLayer.Entity.Theatre;
import demo.DatabaseLayer.Repository.TheatreRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TheatreService {
    private final TheatreRepository theatreRepository;

    @Autowired
    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre findTheatreByTheatreId(String theatreId) {
        return theatreRepository.findById(theatreId).orElseThrow(() -> new EntityNotFoundException("theatre with theatreId '" + theatreId + "' not found!"));
    }

    public List<Theatre> findAll() {
        if (theatreRepository.findAll().isEmpty()) throw new EntityNotFoundException("no theatres in the database!");
        return theatreRepository.findAll();
    }

    public Theatre saveTheatre(Theatre theatre) {
        if (theatre.getTheatreId() == null) theatre.setTheatreId(UUID.randomUUID().toString().replaceAll("-", ""));
        return theatreRepository.save(theatre);
    }

    public List<Theatre> saveTheatres(List<Theatre> theatres) {
        theatres.forEach(theatre -> {
            if (theatre.getTheatreId() == null) theatre.setTheatreId(UUID.randomUUID().toString().replaceAll("-", ""));
        });
        return theatreRepository.saveAll(theatres);
    }

    public void deleteTheatreByTheatreId(String theatreId) {
        theatreRepository.findById(theatreId).orElseThrow(() -> new EntityNotFoundException("theatre with theatreId '" + theatreId + "' not found!"));
        theatreRepository.delete(theatreRepository.findTheatreByTheatreId(theatreId));
    }

    public void deleteAll() {
        theatreRepository.deleteAll();
    }
}
