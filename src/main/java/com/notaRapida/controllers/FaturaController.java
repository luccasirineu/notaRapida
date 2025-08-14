package com.notaRapida.controllers;




import com.notaRapida.dtos.FaturaRequestDTO;
import com.notaRapida.dtos.FaturaResponseDTO;
import com.notaRapida.services.FaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fatura")
@CrossOrigin(origins = "*")
public class FaturaController {

    private static final Logger logger = LoggerFactory.getLogger(FaturaController.class);

    @Autowired
    private FaturaService faturaService;


    @PostMapping
    public ResponseEntity<FaturaResponseDTO> createFatura(@RequestBody FaturaRequestDTO requestDTO) {
        logger.info("Iniciando criação de fatura para o cliente: {} --- Documento:  {}",requestDTO.getClienteDTO().getNome() ,requestDTO.getClienteDTO().getDocumento());

        FaturaResponseDTO response = faturaService.createFatura(requestDTO);

        logger.info("Fatura criada com sucesso. ID {}", response.getId());
        return ResponseEntity.ok(response);
    }


}
