package uz.pdp.springbootdemo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.springbootdemo.entity.User;
import uz.pdp.springbootdemo.exception.BadRequestException;
import uz.pdp.springbootdemo.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // TODO: 13/10/22 Return access and refresh token
        System.out.println("Success...");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        User loginQilganUser = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("G11_MAXFIY_SUZ");

        List<String> roleNames = loginQilganUser.getRoles().stream()
                .map(role -> role.getName().toString()).toList();

        String accessToken = JWT.create()
                .withSubject(loginQilganUser.getUsername())
                .withClaim("userId", loginQilganUser.getId())
                .withClaim("roles", roleNames)
                .withIssuedAt(new Date())
//                .withExpiresAt(new Date(System.currentTimeMillis() + (86_400_000)))
                .withExpiresAt(new Date(System.currentTimeMillis() + (30000)))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(loginQilganUser.getUsername())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (86_400_000 * 7)))
                .sign(algorithm);;

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(400);
        Map<String, String> messages = new HashMap<>();
        messages.put("errorMessage", "Noto'g'ri username or password");
        new ObjectMapper().writeValue(response.getOutputStream(), messages);

    }
}
