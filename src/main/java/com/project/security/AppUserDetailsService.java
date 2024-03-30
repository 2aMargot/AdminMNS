package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    ModelUserD userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<ModelUser> optionalUtilisateur = userDao.findbyEmail(email);

        if(optionalUtilisateur.isPresent()) {

            return new AppUserDetails(optionalUtilisateur.get());
        }

        throw new UsernameNotFoundException("Email introuvable");
    }

}
