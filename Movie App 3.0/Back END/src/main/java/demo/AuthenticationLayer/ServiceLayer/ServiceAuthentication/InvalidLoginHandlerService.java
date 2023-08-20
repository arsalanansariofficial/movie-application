package demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class InvalidLoginHandlerService implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException {
        httpServletResponse.sendError(httpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    }
}
