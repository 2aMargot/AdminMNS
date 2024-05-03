package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import com.project.adminmns.security.AppUserDetails;
import com.project.adminmns.security.JwtUtils;
import com.project.adminmns.view.ModelUserView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@AllArgsConstructor
public class ConnexionController {

    ModelUserDao userDao;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    AuthenticationProvider authenticationProvider;
    JwtUtils jwtUtils;

    @PostMapping("/connexion")
    public ResponseEntity<Map<String, Object>> connexion (@RequestBody ModelUser user){
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

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }


    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> inscription (@Valid @RequestBody ModelUser user){

        //The following restrictions are imposed in the email address’ local part by using this regex:
        //
        //It allows numeric values from 0 to 9.
        //Both uppercase and lowercase letters from a to z are allowed.
        //Allowed are underscore “_”, hyphen “-“, and dot “.”
        //Dot isn’t allowed at the start and end of the local part.
        //Consecutive dots aren’t allowed.

        //For the local part, a maximum of 64 characters are allowed.

        //Restrictions for the domain part in this regular expression include:
        //
        //It allows numeric values from 0 to 9.
        //We allow both uppercase and lowercase letters from a to z.
        //Hyphen “-” and dot “.” aren’t allowed at the start and end of the domain part.
        //No consecutive dots
        String emailRegexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        //Minimum eight characters,
        // at least one uppercase letter,
        // one lowercase letter,
        // one number
        // and one special character
        String passwordRegexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        String emailAddress = user.getEmail();
        String password = user.getPassword();


        if (patternMatches(emailAddress, emailRegexPattern) && patternMatches(password, passwordRegexPattern)){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return new ResponseEntity<>(Map.of("message", "utilisateur créé"), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(Map.of("message", "Email incorrect ou mot de passe incorrect"), HttpStatus.BAD_REQUEST);
        }


    }



    @GetMapping("/profil")
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> profil (@AuthenticationPrincipal AppUserDetails userDetails){

        return new ResponseEntity<>(userDetails.getUser(), HttpStatus.OK);
    }
}
