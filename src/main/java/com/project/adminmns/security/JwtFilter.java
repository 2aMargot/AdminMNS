package com.project.adminmns.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom filter for handling JSON Web Token (JWT) authentication.
 * <p>
 * This filter extends {@link OncePerRequestFilter} and is used to check for a JWT in the HTTP request headers,
 * validate it, and set the authentication in the Spring Security context.
 * </p>
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Processes the HTTP request and performs JWT authentication if a JWT is present in the request headers.
     * <p>
     * This method retrieves the JWT from the "Authorization" header, extracts the subject from the token,
     * loads the user details, and sets the authentication in the Spring Security context.
     * </p>
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain to pass the request and response to the next filter.
     * @throws ServletException If an error occurs during the filtering process.
     * @throws IOException If an I/O error occurs during the filtering process.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if(authorization != null){

            String jwt = authorization.substring(7);
            String subject = jwtUtils.getSubjectFromJwt(jwt);

            UserDetails userDetails = appUserDetailsService.loadUserByUsername(subject);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
