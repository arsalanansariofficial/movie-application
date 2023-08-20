package demo.AuthenticationLayer.ControllerLayer;

import demo.AuthenticationLayer.DatabaseLayer.Entity.Token;
import demo.AuthenticationLayer.DatabaseLayer.Entity.User;
import demo.AuthenticationLayer.ServiceLayer.Model.UserPrincipal;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.TokenService;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @GetMapping(value = "/{email}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Token> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);
        String token = tokenService.generateToken(userService.loadUserByUsername(user.getEmail()));
        Token accessToken = tokenService.saveToken(Token.builder().accessToken(token).user(savedUser).build());
        return new ResponseEntity<>(accessToken, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{email}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>("user deleted!", HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Token> loginUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        User savedUser = userService.findUserByEmail(user.getEmail());
        String token = tokenService.generateToken((UserPrincipal) authentication.getPrincipal());
        Token accessToken = tokenService.saveToken(Token.builder().accessToken(token).user(savedUser).build());
        return new ResponseEntity<>(accessToken, HttpStatus.CREATED);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Object> logout() {
        tokenService.deleteTokenByTokenId();
        return new ResponseEntity<>("session deactivated", HttpStatus.CREATED);
    }

    @PostMapping(value = "/logoutAll")
    public ResponseEntity<Object> logoutAll() {
        tokenService.deleteAllByUser();
        return new ResponseEntity<>("sessions deactivated", HttpStatus.CREATED);
    }
}
