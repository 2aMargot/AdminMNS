package com.project.adminmns.security;

import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    ModelUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<ModelUser> optionalUtilisateur = userDao.findByEmail(email);

        if(optionalUtilisateur.isPresent()) {

            return new AppUserDetails(optionalUtilisateur.get());
        }
        throw new UsernameNotFoundException("Email introuvable");
    }
}
