package com.project.adminmns.security;

import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for providing user details for authentication.
 * <p>
 * This service implements the {@link UserDetailsService} interface and is responsible for loading user-specific data
 * by their email address. It integrates with the {@link ModelUserDao} to retrieve user information.
 * </p>
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    /**
     * Constructs an {@link AppUserDetailsService} with the specified {@link ModelUserDao}.
     *
     * @param userDao The {@link ModelUserDao} used to perform CRUD operations on {@link ModelUser} entities.
     */
    @Autowired
    ModelUserDao userDao;

    /**
     * Loads a {@link UserDetails} object by the provided email.
     * <p>
     * This method is used by the Spring Security framework to retrieve user information for authentication purposes.
     * If a user with the specified email is found, an {@link AppUserDetails} object is returned.
     * If no user is found, a {@link UsernameNotFoundException} is thrown.
     * </p>
     *
     * @param email The email of the user to retrieve.
     * @return A {@link UserDetails} object representing the user.
     * @throws UsernameNotFoundException If a user with the provided email is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<ModelUser> optionalUtilisateur = userDao.findByEmail(email);

        if(optionalUtilisateur.isPresent()) {

            return new AppUserDetails(optionalUtilisateur.get());
        }
        throw new UsernameNotFoundException("Email introuvable");
    }
}
