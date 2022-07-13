package zw.co.nimblecode.doctorsappointmentsystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private String jwtSecurityKey = "qwertyuiop1234567yuitrewwwdfgbhjklkmjnzsd567890hgjdfgtreyuhnbvfgsssjkj8765432rtwuh";

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPassword usernameAndPassword = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPassword.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(usernameAndPassword.getUsername(), usernameAndPassword.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new java.util.Date())
                .setExpiration(Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(jwtSecurityKey.getBytes()))
                .compact();

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
        response.addHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authResult.getPrincipal();
        AuthenticatedUserMinified authenticatedUserMinified = new AuthenticatedUserMinified();
        authenticatedUserMinified.setFullname(authenticatedUser.getUserInformation().get("name"));
        response.getWriter().write(new ObjectMapper().writeValueAsString(authenticatedUserMinified));
        response.getWriter().flush();
    }
}

@Data
class UsernameAndPassword {
    private String username;
    private String password;
}
