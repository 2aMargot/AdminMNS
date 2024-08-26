package com.project.adminmns;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import com.project.adminmns.security.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ConnexionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testConnexion() throws Exception {
        ModelUser user = new ModelUser();
        user.setEmail("test@example.com");
        user.setPassword("password");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);

        mockMvc.perform(post("/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jwt").exists());
    }

    @Test
    void testInscription() throws Exception {
        ModelUser user = new ModelUser();
        user.setEmail("newuser@example.com");
        user.setPassword("password");

        mockMvc.perform(post("/inscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("utilisateur créé"));
    }
}