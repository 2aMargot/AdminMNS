package com.project.adminmns.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Utility class for handling JSON Web Tokens (JWT).
 * <p>
 * This class provides methods to generate JWTs based on user details and extract the subject from a JWT.
 * </p>
 */
@Service
public class JwtUtils {

    @Value("secret.jwt")
    String secretJwt;

    /**
     * Generates a JWT based on the provided {@link UserDetails}.
     * <p>
     * The generated JWT includes the username as the subject and is signed with the configured secret key.
     * </p>
     *
     * @param userDetails The {@link UserDetails} object containing user information.
     * @return The generated JWT as a {@link String}.
     */
    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,secretJwt)
                .setSubject(userDetails.getUsername())
//                .setIssuedAt(Date.from(Instant.ofEpochSecond()))   //création du jwt
//                .setExpiration(Date.from(Instant.ofEpochSecond())  //expiration du jwt
                .compact();

    }

    /**
     * Extracts the subject (email) from the provided JWT.
     * <p>
     * This method parses the JWT using the configured secret key and retrieves the subject from the claims.
     * </p>
     *
     * @param jwt The JWT as a {@link String}.
     * @return The subject extracted from the JWT.
     * @throws io.jsonwebtoken.JwtException If the JWT is invalid or cannot be parsed.
     */
    public String getSubjectFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey(secretJwt)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
