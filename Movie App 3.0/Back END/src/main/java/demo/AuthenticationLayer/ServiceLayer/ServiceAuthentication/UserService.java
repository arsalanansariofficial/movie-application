package demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication;

import demo.AuthenticationLayer.DatabaseLayer.Entity.User;
import demo.AuthenticationLayer.DatabaseLayer.Repository.UserRepository;
import demo.AuthenticationLayer.ServiceLayer.Model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException("email with id " + email + " does not exist"));
        return UserPrincipal.create(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new EntityNotFoundException("email with id " + email + " does not exist"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUserByEmail(String email) {
        userRepository.delete(findUserByEmail(email));
    }
}
