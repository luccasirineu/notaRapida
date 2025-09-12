package com.notaRapida.controllers;

import com.notaRapida.dtos.DashboardDTO;
import com.notaRapida.services.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);


    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard() {
        DashboardDTO dashboard = dashboardService.getDashboardData();

        logger.info("Informações do dashboard puxado do banco: {}", dashboard.toString());

        return ResponseEntity.ok(dashboard);
    }


}