package com.notaRapida.controllers;




import com.notaRapida.dtos.FaturaRequestDTO;
import com.notaRapida.dtos.FaturaResponseDTO;
import com.notaRapida.services.FaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fatura")
@CrossOrigin(origins = "*")
public class FaturaController {

    @Autowired
    private FaturaService faturaService;


    @PostMapping
    public ResponseEntity<FaturaResponseDTO> createFatura(@RequestBody FaturaRequestDTO requestDTO) {
        FaturaResponseDTO response = faturaService.createFatura(requestDTO);
        return ResponseEntity.ok(response);
    }

    /*

    @GetMapping
    public ResponseEntity<List<FaturaResponseDTO>> getAllInvoices() {
        List<FaturaResponseDTO> invoices = faturaService.getAllFaturas();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturaResponseDTO> getInvoiceById(@PathVariable Long id) {
        FaturaResponseDTO invoice = faturaService.getFaturaById(id);
        return ResponseEntity.ok(invoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        faturaService.deleteFatura(id);
        return ResponseEntity.noContent().build();
    }

     */
}
