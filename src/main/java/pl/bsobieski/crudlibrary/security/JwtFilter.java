package pl.bsobieski.crudlibrary.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Set;

public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if(token != null && !token.equals(""))
        {
            UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(token);
            SecurityContextHolder.getContext().setAuthentication(authResult);
        }
        chain.doFilter(request,response);
    }

    //TODO change secret key
    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString("cLSMQk$h~cYik?k".getBytes()))
                .parseClaimsJws(token.replace("Bearer ",""));

        String username = claimsJws.getBody().get("sub").toString();
        String firstName = claimsJws.getBody().get("firstName").toString();
        String lastName = claimsJws.getBody().get("lastName").toString();
        String emailAdress = claimsJws.getBody().get("emailAdress").toString();
        String role = claimsJws.getBody().get("role").toString();

        //TODO get user from database and check

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(username,null,simpleGrantedAuthorities);
    }
}
