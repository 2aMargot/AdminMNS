package com.project.adminmns.controller;

import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import com.project.adminmns.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class ConnexionController {

    private final ModelUserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;

    @Autowired
    public ConnexionController(ModelUserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationProvider authenticationProvider, JwtUtils jwtUtils){
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/connexion")
    public ResponseEntity<Map<String, Object>> connexion(@RequestBody ModelUser user){
        try{
            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword())).getPrincipal();
            return new ResponseEntity<>(Map.of("jwt", jwtUtils.generateToken(userDetails)), HttpStatus.OK);
        }catch (AuthenticationException ex){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> inscription (@RequestBody ModelUser user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDao.save(user);

        return new ResponseEntity<>(Map.of("message", "utilisateur créé"), HttpStatus.CREATED);
    }
}
