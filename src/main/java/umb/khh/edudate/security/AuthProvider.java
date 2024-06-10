package umb.khh.edudate.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.User;

import java.util.Base64;
import java.util.Date;

import com.auth0.jwt.JWT;

@RequiredArgsConstructor
@Component
public class AuthProvider {

    private String secretKey = "KHH - Best team ever";

    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createJWTToken(User user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 36000000);

        System.out.println(JWT.create().
                withIssuer(user.getUsername()).
                withIssuedAt(now).
                withExpiresAt(validity).
                withIssuer("username:" + user.getUsername()).
                sign(Algorithm.HMAC256(secretKey)));

        return JWT.create().
                withIssuer(user.getUsername()).
                withIssuedAt(now).
                withExpiresAt(validity).
                withIssuer("username:" + user.getUsername()).
                sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication checkJWTToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decodedJWT = verifier.verify(token);

        String issuer = decodedJWT.getIssuer();
        String[] parts = issuer.split(":");

        UserDTO user = UserDTO.builder()
                .username(parts[1]).
                password(decodedJWT.getClaim("password").asString()).
                build();

        return new UsernamePasswordAuthenticationToken(user, null, null);
    }
}
