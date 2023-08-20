package demo.ControllerLayer;

import demo.DatabaseLayer.Entity.Ticket;
import demo.ServiceLayer.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Ticket> findTicketByTicketId(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.findTicketByTicketId(ticket.getTicketId()), HttpStatus.OK);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Ticket>> findAll() {
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Ticket> saveTicket(@Valid @RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.saveTicket(ticket), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Ticket> deleteTicketById(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.findTicketByTicketId(ticket.getTicketId());
        ticketService.deleteTicketByTicketId(ticket.getTicketId());
        return new ResponseEntity<>(savedTicket, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<List<Ticket>> deleteAll() {
        List<Ticket> savedCities = ticketService.findAll();
        ticketService.deleteAll();
        return new ResponseEntity<>(savedCities, HttpStatus.OK);
    }
}
