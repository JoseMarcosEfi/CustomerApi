package com.apiCustomer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.api.customer.controller.AuthController;
import com.api.customer.jwt.JwtService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginSuccess() {

        String username = "testuser";
        String password = "testpassword";
        String token = "testtoken";
        Map<String, String> request = Map.of("username", username, "password", password);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(null);


        when(jwtService.generateToken(username)).thenReturn(token);

        Map<String, String> response = authController.login(request);

        assertEquals(token, response.get("token"));

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken(username);
    }
    @Test
    public void testLoginFailure() {
        // Dados de entrada inválidos
        String username = "wronguser";
        String password = "wrongpassword";
        Map<String, String> request = Map.of("username", username, "password", password);

        // Simular falha na autenticação (credenciais inválidas)
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenThrow(new BadCredentialsException("Credenciais inválidas"));

        // Chamar o método do controller e capturar a exceção
        try {
            authController.login(request);
        } catch (Exception e) {
            assertEquals("Credenciais inválidas", e.getMessage());
        }

        // Verificar chamadas dos mocks
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, never()).generateToken(anyString()); // O token NÃO deve ser gerado
    }

    @Test
    public void testLoginSuccessWithMockMvc() throws Exception {
        // Simular resposta do JWT
        String token = "mockedToken";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(null);

        when(jwtService.generateToken("testuser")).thenReturn(token);

        // Simular requisição POST para autenticação
        String jsonRequest = """
            {
                "username": "testuser",
                "password": "testpassword"
            }
        """;

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk()) // Deve retornar 200 OK
                .andExpect(jsonPath("$.token").value(token)); // Verifica o token retornado
    }
    
    @Test
    public void testLoginFailureWithMockMvc() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenThrow(new BadCredentialsException("Credenciais inválidas"));

        String jsonRequest = """
            {
                "username": "wronguser",
                "password": "wrongpassword"
            }
        """;

        mockMvc.perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isUnauthorized()) // Deve retornar 401 Unauthorized
                .andExpect(content().string("Credenciais inválidas")); // Verifica a mensagem de erro
    }

}
