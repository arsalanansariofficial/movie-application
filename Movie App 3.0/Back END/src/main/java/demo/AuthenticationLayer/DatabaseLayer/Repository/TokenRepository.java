package demo.AuthenticationLayer.DatabaseLayer.Repository;

import demo.AuthenticationLayer.DatabaseLayer.Entity.Token;
import demo.AuthenticationLayer.DatabaseLayer.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    List<Token> findTokensByUser(User user);
}
