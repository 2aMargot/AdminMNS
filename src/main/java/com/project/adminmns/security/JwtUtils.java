package com.project.adminmns.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    @Value("secret.jwt")
    String secretJwt;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"secretJwt")
                .setSubject(userDetails.getUsername())
//                .setIssuedAt(Date.from(Instant.ofEpochSecond()))   //création du jwt
//                .setExpiration(Date.from(Instant.ofEpochSecond())  //expiration du jwt
                .compact();

    }

    public String getSubjectFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey("secretJwt")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
