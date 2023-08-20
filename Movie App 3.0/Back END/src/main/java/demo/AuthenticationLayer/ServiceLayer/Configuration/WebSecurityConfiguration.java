package demo.AuthenticationLayer.ServiceLayer.Configuration;

import demo.AuthenticationLayer.ServiceLayer.Filter.JwtAuthenticationFilter;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.InvalidLoginHandlerService;
import demo.AuthenticationLayer.ServiceLayer.ServiceAuthentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] authorizedRoutes = { "/user/save/**", "/user/login/**", "/object-adapter/users", "/object-adapter/users/login", "/object-adapter/movies/**", "/object-adapter/upcoming-movies/**", "/movie/**", "/city/**", "/theatres/**", "/movie-theatre/**", "/movie-show/**", "/ticket/**"};
    private final UserService userService;
    private final InvalidLoginHandlerService invalidLoginHandlerService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfiguration(UserService userService, InvalidLoginHandlerService invalidLoginHandlerService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userService = userService;
        this.invalidLoginHandlerService = invalidLoginHandlerService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(invalidLoginHandlerService)
                .and()
                .authorizeRequests()
                .antMatchers(authorizedRoutes)
                .permitAll()
                .anyRequest()
                .authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
