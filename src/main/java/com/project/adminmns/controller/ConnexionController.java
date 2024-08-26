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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class ConnexionController {

    private final ModelUserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;

    /**
     * Constructor for the ConnexionController class.
     *
     * @param userDao The ModelUserDao object used for accessing user data.
     * @param bCryptPasswordEncoder The BCryptPasswordEncoder object used for encoding passwords.
     * @param authenticationProvider The AuthenticationProvider object used for user authentication.
     * @param jwtUtils The JwtUtils object used for managing JWT tokens.
     */
    @Autowired
    public ConnexionController(ModelUserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationProvider authenticationProvider, JwtUtils jwtUtils){
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Handles user login requests.
     *
     * @param user The ModelUser object containing the user's login credentials.
     * @return A ResponseEntity containing a map with the generated JWT token if authentication is successful, or a FORBIDDEN status if authentication fails.
     */
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

    /**
     * Handles user registration requests.
     *
     * @param user The ModelUser object containing the user's registration details.
     * @return A ResponseEntity containing a map with a success message if the user is successfully created, with a CREATED status.
     */
    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> inscription (@RequestBody ModelUser user){
        Optional<ModelUser> modelUser = userDao.findByEmail(user.getEmail());
        if(modelUser.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDao.save(user);

        return new ResponseEntity<>(Map.of("message", "utilisateur créé"), HttpStatus.CREATED);
    }
}
