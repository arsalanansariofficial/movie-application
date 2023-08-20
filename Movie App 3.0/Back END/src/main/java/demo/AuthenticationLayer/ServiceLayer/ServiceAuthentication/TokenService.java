package demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication;

import demo.AuthenticationLayer.DatabaseLayer.Entity.Token;
import demo.AuthenticationLayer.DatabaseLayer.Repository.TokenRepository;
import demo.AuthenticationLayer.ServiceLayer.Model.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenService {
    private static final String JWT_PRIVATE_KEY = "JWT_PRIVATE_KEY";
    private static final Integer TOKEN_EXPIRATION_TIME = 60 * 60 * 1000;
    private final TokenRepository tokenRepository;
    private String token;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String generateToken(UserPrincipal userPrincipal) {
        List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder().setIssuer("issuer").setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + TOKEN_EXPIRATION_TIME)).claim("Roles", roles).signWith(SignatureAlgorithm.HS512, JWT_PRIVATE_KEY).compact();
    }

    public boolean validateToken(String token) {
        this.token = token;
        try {
            Jwts.parser().setSigningKey(JWT_PRIVATE_KEY).parseClaimsJws(token);
            findTokenByTokenId(token);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            return false;
        }
        return true;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_PRIVATE_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public Token findTokenByTokenId(String tokenId) {
        return tokenRepository.findById(tokenId).orElseThrow(() -> new EntityNotFoundException("Authentication token expired!"));
    }

    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    public void deleteTokenByTokenId() {
        try {
            tokenRepository.findById(token);
            tokenRepository.deleteById(token);
        } catch (Exception exception) {
            System.out.println("deleteTokenByTokenId: " + exception.getMessage());
        }
    }

    public void deleteAllByUser() {
        try {
            List<Token> tokens = tokenRepository.findTokensByUser(findTokenByTokenId(token).getUser());
            if (tokens.size() != 0) tokenRepository.deleteAll(tokens);
        } catch (Exception exception) {
            System.out.println("deleteAllByUser: " + exception.getMessage());
        }
    }
}
