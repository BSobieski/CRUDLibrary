package pl.bsobieski.crudlibrary.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.bsobieski.crudlibrary.entities.User;
import pl.bsobieski.crudlibrary.services.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private UserService userService;

    public JwtFilter() {
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token != null && !token.equals("")) {
            try {
                UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(token);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            } catch (NoSuchElementException|NullPointerException ignored) { }
        }
        chain.doFilter(request, response);
    }

    //TODO change secret key
    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString("cLSMQk$h~cYik?k".getBytes()))
                .parseClaimsJws(token.replace("Bearer ", ""));

        String username = claimsJws.getBody().get("sub").toString();
        String firstName = claimsJws.getBody().get("firstName").toString();
        String lastName = claimsJws.getBody().get("lastName").toString();
        String emailAddress = claimsJws.getBody().get("emailAdress").toString();
        String role = claimsJws.getBody().get("role").toString();

        User userFromToken = userService.getUserByUsername(username).orElseThrow(() -> new NoSuchElementException());

        if (!userFromToken.getFirstName().equals(firstName) ||
                !userFromToken.getLastName().equals(lastName) ||
                !userFromToken.getEmailAddress().equals(emailAddress) ||
                !userFromToken.getRole().equals(role.replace("ROLE_",""))) {
            throw new NoSuchElementException();
        }

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
    }
}
