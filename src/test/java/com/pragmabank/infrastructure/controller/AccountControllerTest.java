package com.pragmabank.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragmabank.domain.model.Account;
import com.pragmabank.domain.model.Account.AccountStatus;
import com.pragmabank.domain.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
@DisplayName("Pruebas de integración para AccountController")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    private Account cuentaPrueba;
    private UUID cuentaId;

    @BeforeEach
    void setUp() {
        cuentaId = UUID.randomUUID();
        cuentaPrueba = new Account(
            cuentaId,
            "ACC-2024-001",
            UUID.randomUUID(),
            new BigDecimal("1000.00"),
            AccountStatus.ACTIVE,
            LocalDateTime.now(),
            null,
            null
        );
    }

    @Test
    @DisplayName("GET /api/cuentas/{id} - debe retornar cuenta cuando existe")
    void getAccount_debeRetornarCuentaCuandoExiste() throws Exception {
        when(accountService.buscarPorId(cuentaId)).thenReturn(Optional.of(cuentaPrueba));

        mockMvc.perform(get("/api/cuentas/{id}", cuentaId.toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(cuentaId.toString()))
            .andExpect(jsonPath("$.accountNumber").value("ACC-2024-001"))
            .andExpect(jsonPath("$.balance").value(1000.00))
            .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    @DisplayName("GET /api/cuentas/{id} - debe retornar 404 cuando no existe")
    void getAccount_debeRetornar404CuandoNoExiste() throws Exception {
        UUID idInexistente = UUID.randomUUID();
        when(accountService.buscarPorId(idInexistente)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cuentas/{id}", idInexistente.toString()))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/cuentas/numero/{accountNumber} - debe retornar cuenta por número")
    void getAccountByNumber_debeRetornarCuentaPorNumero() throws Exception {
        when(accountService.buscarPorNumero("ACC-2024-001")).thenReturn(Optional.of(cuentaPrueba));

        mockMvc.perform(get("/api/cuentas/numero/{accountNumber}", "ACC-2024-001"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accountNumber").value("ACC-2024-001"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas - debe crear cuenta exitosamente")
    void createAccount_debeCrearCuentaExitosamente() throws Exception {
        when(accountService.crearCuenta(any(), eq("ACC-2024-001"), any(BigDecimal.class)))
            .thenReturn(cuentaPrueba);

        String requestBody = "{\"customerId\":\"" + cuentaPrueba.getCustomerId() + "\",\"accountNumber\":\"ACC-2024-001\",\"initialBalance\":1000.00}";

        mockMvc.perform(post("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.accountNumber").value("ACC-2024-001"))
            .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas - debe retornar 400 cuando datos inválidos")
    void createAccount_debeRetornar400CuandoDatosInvalidos() throws Exception {
        String requestBody = "{\"accountNumber\":\"\",\"initialBalance\":-100}";

        mockMvc.perform(post("/api/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas/{id}/depositar - debe realizar depósito exitosamente")
    void deposit_debeRealizarDepositoExitosamente() throws Exception {
        when(accountService.depositar(eq(cuentaId), any(BigDecimal.class))).thenReturn(cuentaPrueba);

        String requestBody = "{\"amount\":500.00}";

        mockMvc.perform(post("/api/cuentas/{id}/depositar", cuentaId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(cuentaId.toString()));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas/{id}/retirar - debe realizar retiro exitosamente")
    void withdraw_debeRealizarRetiroExitosamente() throws Exception {
        when(accountService.retirar(eq(cuentaId), any(BigDecimal.class))).thenReturn(cuentaPrueba);

        String requestBody = "{\"amount\":200.00}";

        mockMvc.perform(post("/api/cuentas/{id}/retirar", cuentaId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(cuentaId.toString()));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/cuentas/{id}/freezar - debe freezar cuenta con rol ADMIN")
    void freeze_debeFreezarCuentaConRolAdmin() throws Exception {
        Account cuentaFrozen = new Account(
            cuentaId,
            "ACC-2024-001",
            cuentaPrueba.getCustomerId(),
            new BigDecimal("1000.00"),
            AccountStatus.FROZEN,
            LocalDateTime.now(),
            null,
            null
        );
        when(accountService.freezeAccount(cuentaId)).thenReturn(cuentaFrozen);

        mockMvc.perform(post("/api/cuentas/{id}/freezar", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("FROZEN"));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("POST /api/cuentas/{id}/freezar - debe retornar 403 con rol USER")
    void freeze_debeRetornar403ConRolUser() throws Exception {
        mockMvc.perform(post("/api/cuentas/{id}/freezar", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("DELETE /api/cuentas/{id} - debe eliminar cuenta con rol ADMIN")
    void delete_debeEliminarCuentaConRolAdmin() throws Exception {
        mockMvc.perform(delete("/api/cuentas/{id}", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("DELETE /api/cuentas/{id} - debe retornar 403 con rol USER")
    void delete_debeRetornar403ConRolUser() throws Exception {
        mockMvc.perform(delete("/api/cuentas/{id}", cuentaId.toString())
                .with(csrf()))
            .andExpect(status().isForbidden());
    }
}