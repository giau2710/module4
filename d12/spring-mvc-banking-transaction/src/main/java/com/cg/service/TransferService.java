package com.cg.service;

import com.cg.model.Transfer;
import com.cg.model.dto.TransferDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface TransferService extends IGeneralService<Transfer>{
    List<TransferDTO> fillAllTransferDTO();
    BigDecimal totalFessAmount();
}
