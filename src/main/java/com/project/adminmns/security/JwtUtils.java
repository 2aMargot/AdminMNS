package com.project.adminmns.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"adeterminerethasher")
//                        TextCodec.BASE64.decode(""))    //hash du 'secret'
                .setSubject(userDetails.getUsername())
//                .setIssuedAt(Date.from(Instant.ofEpochSecond()))   //création du jwt
//                .setExpiration(Date.from(Instant.ofEpochSecond())  //expiration du jwt
                .compact();

    }

    public String getSubjectFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey("adeterminerethasher")
//                .setSigningKeyResolver(secretService.getSigningKeyResolver()) //méthode de génération dynamique du 'secret'
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
