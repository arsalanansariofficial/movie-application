package demo.AdapterLayer.ObjectAdapter.ControllerAdapter;

import demo.AdapterLayer.ObjectAdapter.Model.MovieModel;
import demo.AdapterLayer.ObjectAdapter.ServiceAdapter.ObjectService;
import demo.AuthenticationLayer.DatabaseLayer.Entity.Token;
import demo.AuthenticationLayer.DatabaseLayer.Entity.User;
import demo.AuthenticationLayer.ServiceLayer.Model.UserPrincipal;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.TokenService;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.UserService;
import demo.DatabaseLayer.Entity.Movie;
import demo.DatabaseLayer.Entity.Ticket;
import demo.ServiceLayer.Service.MovieService;
import demo.ServiceLayer.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/object-adapter")
public class ObjectController {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ObjectService serviceAdapter;
    private final MovieService movieService;
    private final TicketService ticketService;

    @Autowired
    public ObjectController(ObjectService serviceAdapter, UserService userService, TokenService tokenService, AuthenticationManager authenticationManager, MovieService movieService, TicketService ticketService) {
        this.serviceAdapter = serviceAdapter;
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }

    public Map<String, Object> getUserObject(User savedUser) {
        Map<String, Object> userObject = new HashMap<>();
        userObject.put("_id", savedUser.getEmail());
        userObject.put("name", savedUser.getName());
        return userObject;
    }

    public Map<String, Object> getTokenObject(String token, Map<String, Object> userObject) {
        Map<String, Object> tokenObject = new HashMap<>();
        tokenObject.put("token", token);
        tokenObject.put("user", userObject);
        return tokenObject;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Map<String, Object>> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        String token = tokenService.generateToken(userService.loadUserByUsername(user.getEmail()));
        tokenService.saveToken(Token.builder().accessToken(token).user(savedUser).build());
        return new ResponseEntity<>(getTokenObject(token, getUserObject(savedUser)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        User savedUser = userService.findUserByEmail(user.getEmail());
        String token = tokenService.generateToken((UserPrincipal) authentication.getPrincipal());
        tokenService.saveToken(Token.builder().accessToken(token).user(savedUser).build());
        return new ResponseEntity<>(getTokenObject(token, getUserObject(savedUser)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/logout")
    public ResponseEntity<Map<String, Object>> logout() {
        tokenService.deleteTokenByTokenId();
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("code", 201);
        responseObject.put("message", "session deactivated");
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/logoutAll")
    public ResponseEntity<Map<String, Object>> logoutAll() {
        tokenService.deleteAllByUser();
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("code", 201);
        responseObject.put("message", "sessions deactivated");
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }

    @PostMapping(value = "/movies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }

    @GetMapping(value = "/movies/{movieId}")
    public ResponseEntity<MovieModel> findMovieByMovieId(@PathVariable String movieId) {
        return new ResponseEntity<>(serviceAdapter.findMovieByMovieId(movieId), HttpStatus.OK);
    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<MovieModel>> findAllMovies() {
        return new ResponseEntity<>(serviceAdapter.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/upcoming-movies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> saveUpcomingMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }

    @GetMapping(value = "/upcoming-movies/{movieId}")
    public ResponseEntity<Movie> findUpcomingMovieByMovieId(@PathVariable String movieId) {
        return new ResponseEntity<>(movieService.findMovieByMovieId(movieId), HttpStatus.OK);
    }

    @GetMapping(value = "/upcoming-movies")
    public ResponseEntity<List<Map<String, Object>>> findAllUpcomingMovies() {
        List<Movie> movies = movieService.findAll().stream().filter(movie -> !movie.getReleasedStatus()).collect(Collectors.toList());
        List<Map<String, Object>> upcomingMovies = movies.stream().map(movie -> {
            Map<String, Object> upcomingMovie = new HashMap<>();
            upcomingMovie.put("_id", movie.getMovieId());
            upcomingMovie.put("posterURL", movie.getPosterURL());
            return upcomingMovie;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(upcomingMovies, HttpStatus.OK);
    }

    @PostMapping(value = "/tickets")
    public ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.saveTicket(ticket), HttpStatus.CREATED);
    }
}
