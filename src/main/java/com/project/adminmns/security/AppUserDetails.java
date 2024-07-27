package com.project.adminmns.security;

import com.project.adminmns.model.ModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Implementation of {@link UserDetails} for integrating custom user details with Spring Security.
 * <p>
 * This class wraps a {@link ModelUser} object and provides the necessary user details for authentication
 * and authorization in Spring Security.
 * </p>
 */
public class AppUserDetails implements UserDetails {

    protected ModelUser user;

    /**
     * Constructs an {@link AppUserDetails} with the specified {@link ModelUser}.
     *
     * @param user The {@link ModelUser} object containing user details.
     */
    public AppUserDetails(ModelUser user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user.
     * <p>
     * The authorities are derived from the user's role. The role is prefixed with "ROLE_" as required by Spring Security.
     * </p>
     *
     * @return A collection of {@link GrantedAuthority} objects representing the user's authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
    }

    public ModelUser getUser(){
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     * <p>
     * In this implementation, the username is represented by the user's email.
     * </p>
     *
     * @return The email of the user.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the user's account has expired.
     * <p>
     * In this implementation, the account is never considered expired.
     * </p>
     *
     * @return {@code true} as the account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     * <p>
     * In this implementation, the account is never considered locked.
     * </p>
     *
     * @return {@code true} as the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials have expired.
     * <p>
     * In this implementation, the credentials are never considered expired.
     * </p>
     *
     * @return {@code true} as the credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * <p>
     * In this implementation, the user is always considered enabled.
     * </p>
     *
     * @return {@code true} as the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
