package com.notaRapida.services;

import com.notaRapida.controllers.FaturaController;
import com.notaRapida.dtos.ClienteDTO;
import com.notaRapida.dtos.FaturaRequestDTO;
import com.notaRapida.dtos.FaturaResponseDTO;
import com.notaRapida.dtos.ItemFaturaRequestDTO;
import com.notaRapida.models.Cliente;
import com.notaRapida.models.Fatura;
import com.notaRapida.repositories.ClienteRepository;
import com.notaRapida.repositories.FaturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FaturaServiceTest {


    @Mock
    private FaturaRepository faturaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private FaturaService faturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void deveCriarFaturaComClienteNovo() {
        // Arrange
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Maria Souza");
        clienteDTO.setDocumento("98765432100");

        FaturaRequestDTO faturaDTO = new FaturaRequestDTO();
        faturaDTO.setObservacoes("Fatura Cliente Novo");
        faturaDTO.setValorTotal(new BigDecimal("200.00"));
        faturaDTO.setClienteDTO(clienteDTO);

        // Simula que o documento não existe
        when(clienteRepository.findByDocumento("98765432100"))
                .thenReturn(Optional.empty());

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setId(2L);
        clienteSalvo.setNome("Maria Souza");
        clienteSalvo.setDocumento("98765432100");

        // Simula o save do novo cliente
        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(clienteSalvo);

        Fatura faturaSalva = new Fatura();
        faturaSalva.setId(2L);
        faturaSalva.setObservacoes("Fatura Cliente Novo");
        faturaSalva.setValorTotal(new BigDecimal("200.00"));
        faturaSalva.setCliente(clienteSalvo);

        // Simula o save da fatura
        when(faturaRepository.save(any(Fatura.class)))
                .thenReturn(faturaSalva);

        // Act
        FaturaResponseDTO resultado = faturaService.createFatura(faturaDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals("Fatura Cliente Novo", resultado.getObservacoes());
        assertEquals(new BigDecimal("200.00"), resultado.getValorTotal());
        assertEquals("Maria Souza", resultado.getClienteDTO().getNome());
        assertEquals("98765432100", resultado.getClienteDTO().getDocumento());

        // Verifica que o cliente novo foi salvo
        verify(clienteRepository, times(1)).save(any(Cliente.class));
        // Verifica que a fatura foi salva
        verify(faturaRepository, times(1)).save(any(Fatura.class));
    }

    @Test
    void deveCriarFaturaComClienteExistente() {


        Cliente clienteExistente = new Cliente();

        clienteExistente.setDocumento("12345678901");

        when(clienteRepository.findByDocumento("12345678901"))
                .thenReturn(Optional.of(clienteExistente));

        Fatura faturaSalva = new Fatura();
        faturaSalva.setId(1L);
        faturaSalva.setCliente(clienteExistente);

        when(faturaRepository.save(any(Fatura.class)))
                .thenAnswer(invocation -> {
                    Fatura fatura = invocation.getArgument(0);
                    fatura.setId(1L);
                    return fatura;
                });

        FaturaRequestDTO request = new FaturaRequestDTO();
        request.setNomeFatura("Fatura Teste");
        request.setClienteDTO(new ClienteDTO("Nome", "email@test.com", "end", "cidade", "uf", "12345-000", "12345678901"));
        request.setItens(List.of(new ItemFaturaRequestDTO("Item A", 2, 50.0)));

        FaturaResponseDTO response = faturaService.createFatura(request);

        assertNotNull(response.getId());
        assertEquals("Fatura Teste", response.getNomeFatura());
        verify(clienteRepository, never()).save(any(Cliente.class)); // cliente já existia
        verify(faturaRepository, times(1)).save(any(Fatura.class));
    }


}