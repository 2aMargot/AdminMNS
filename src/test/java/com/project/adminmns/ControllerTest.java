package com.project.adminmns;

import com.project.adminmns.controller.ConnexionController;
import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import com.project.adminmns.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConnexionControllerTest {

    @Mock
    private ModelUserDao userDao;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private ConnexionController connexionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConnexion_Success() {
        ModelUser user = new ModelUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        UserDetails userDetails = mock(UserDetails.class);
        when(authenticationProvider.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(userDetails, user.getPassword()));
        when(jwtUtils.generateToken(userDetails)).thenReturn("mockJwtToken");

        ResponseEntity<Map<String, Object>> response = connexionController.connexion(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mockJwtToken", response.getBody().get("jwt"));
    }

    @Test
    void testConnexion_Failure() {
        ModelUser user = new ModelUser();
        user.setEmail("test@example.com");
        user.setPassword("wrongpassword");

        when(authenticationProvider.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        ResponseEntity<Map<String, Object>> response = connexionController.connexion(user);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void testInscription_Success() {
        ModelUser user = new ModelUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        ResponseEntity<Map<String, Object>> response = connexionController.inscription(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("utilisateur créé", response.getBody().get("message"));
        verify(userDao, times(1)).save(any(ModelUser.class));
    }
}