package com.cg.service;

import com.cg.model.Transfer;
import com.cg.model.dto.TransferDTO;
import com.cg.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TransferImpl implements TransferService{
    @Autowired
    TransferRepository transferRepository;

    @Override
    public List<Transfer> findAll() {
        return null;
    }

    @Override
    public Optional<Transfer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Transfer getById(Long id) {
        return null;
    }

    @Override
    public Transfer save(Transfer transfer) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(Transfer transfer) {

    }

    @Override
    public List<TransferDTO> fillAllTransferDTO() {
        return null;
    }
}
