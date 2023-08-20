package demo.AuthenticationLayer.ServiceLayer.Filter;

import demo.AuthenticationLayer.ServiceLayer.Model.UserPrincipal;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.TokenService;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public JwtAuthenticationFilter(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization") != null ? request.getHeader("Authorization").split(" ")[1] : null;
        if (token != null && tokenService.validateToken(token)) {
            String email = tokenService.getEmailFromToken(token);
            UserPrincipal userPrincipal = userService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
