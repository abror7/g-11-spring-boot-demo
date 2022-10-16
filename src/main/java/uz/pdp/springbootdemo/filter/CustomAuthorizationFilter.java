package uz.pdp.springbootdemo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.springbootdemo.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String url = request.getServletPath();
        if (url.equals("/api/login") || url.equals("/api/auth/register") || url.equals("/api/auth/refresh-token")) {
            filterChain.doFilter(request, response);
        } else {
            String requestHeader = request.getHeader(AUTHORIZATION);
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                Map<String, String> messages = new HashMap<>();
                try {

                    String accessToken = requestHeader.substring(7);
                    Algorithm algorithm = Algorithm.HMAC256("G11_MAXFIY_SUZ");
                    JWTVerifier verifier = JWT.require(algorithm).build();

                    DecodedJWT decodedJWT = verifier.verify(accessToken);
                    String username = decodedJWT.getSubject();
                    String[] roleNames = decodedJWT.getClaim("roles").asArray(String.class);

                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                    stream(roleNames).forEach(roleName -> authorities.add(new SimpleGrantedAuthority(roleName)));

                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    filterChain.doFilter(request, response);

                }
                catch (TokenExpiredException e){
                    messages.put("errorMessages", "Token muddati tugagan!!!");
                    e.printStackTrace();
                }
                catch (SignatureVerificationException e){
                    messages.put("errorMessages", "Token buzilgan!!!");
                    e.printStackTrace();
                }
                catch (JWTDecodeException e) {
                    messages.put("errorMessages", "Token buzilgan!!!");
                    e.printStackTrace();
                }catch (Exception e){
                    messages.put("errorMessages", "Something is wrong with jwt token!!!");
                    e.printStackTrace();
                }
                finally {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    new ObjectMapper().writeValue(response.getOutputStream(), messages);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }


    }
}
