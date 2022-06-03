package com.cg.controller.rest;

import com.cg.model.Transfer;
import com.cg.model.dto.TransferDTO;
import com.cg.repository.TransferRepository;
import com.cg.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferRestController {
    @Autowired
    TransferService transferService;
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TransferDTO> transfersDTOS =transferService.fillAllTransferDTO();
        return new ResponseEntity<>(transfersDTOS,HttpStatus.OK);
    }
}
