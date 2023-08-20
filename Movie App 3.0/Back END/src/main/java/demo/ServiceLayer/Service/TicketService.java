package demo.ServiceLayer.Service;

import demo.DatabaseLayer.Entity.MovieShow;
import demo.DatabaseLayer.Entity.Ticket;
import demo.DatabaseLayer.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MovieShowService movieShowService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, MovieShowService movieShowService) {
        this.ticketRepository = ticketRepository;
        this.movieShowService = movieShowService;
    }

    public Ticket findTicketByTicketId(String ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("ticket with ticketId '" + ticketId + "' not found!"));
    }

    public List<Ticket> findAll() {
        if (ticketRepository.findAll().isEmpty()) throw new EntityNotFoundException("no tickets in the database!");
        return ticketRepository.findAll();
    }

    public Ticket saveTicket(Ticket ticket) {
        if (ticket.getTicketId() == null) ticket.setTicketId(UUID.randomUUID().toString().replaceAll("-", ""));

        MovieShow movieShow = movieShowService.findMovieShowByShowMovieShowId(ticket.getMovieShowId());
        Integer seatsAvailable = movieShow.getSeatsAvailable() - Integer.parseInt(ticket.getNumberOfTickets());
        movieShow.setSeatsAvailable(seatsAvailable);
        movieShowService.updateMovieShow(movieShow);

        return ticketRepository.save(ticket);
    }

    public void deleteTicketByTicketId(String ticketId) {
        ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("ticket with ticketId '" + ticketId + "' not found!"));
        ticketRepository.delete(findTicketByTicketId(ticketId));
    }

    public void deleteAll() {
        ticketRepository.deleteAll();
    }

}
