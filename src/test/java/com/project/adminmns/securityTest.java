//package com.project.adminmns;
//
//import com.project.adminmns.model.ModelUser;
//import com.project.adminmns.security.JwtUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Map;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ConnexionControllerSecurityTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    private String token;
//
//    private String invalidToken = "someInvalidToken";
//    @BeforeEach
//    void setUp() {
//        ModelUser user = new ModelUser();
//        user.setEmail("secure@example.com");
//        user.setPassword(bCryptPasswordEncoder.encode("password"));
//
//        UsernamePasswordAuthenticationToken authentication =
//                new UsernamePasswordAuthenticationToken(user.getEmail(), null);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        token = jwtUtils.generateToken(user);
//    }
//
//    @Test
//    void testAccessProtectedEndpointWithValidToken() throws Exception {
//        mockMvc.perform(post("/protected-endpoint")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testAccessDeleteUserWithInvalidToken() throws Exception {
//        mockMvc.perform(delete("/users/5")
//                        .header("Authorization", "Bearer " + invalidToken)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void testAccessDeleteUserWithoutToken() throws Exception {
//        mockMvc.perform(delete("/users/5")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnauthorized());
//    }
//}