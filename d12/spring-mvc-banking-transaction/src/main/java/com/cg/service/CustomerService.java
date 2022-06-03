package com.cg.service;

import com.cg.model.*;
import com.cg.model.dto.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CustomerService extends IGeneralService<Customer> {
    List<CustomerDTO> fillAllActive();

    List<Customer> fillAllRecipient(Long senderId);

    List<Transfer> fillAllTransfer();

    Optional<CustomerDTO> findCustomerDTOById(Long id);

    CustomerDTO getCustomerDTOById(Long id);

    void incrementBalance(Customer customer, BigDecimal transactionAmount);

    void reduceBalance(Customer customer,BigDecimal transactionAmount);

    void doDeposit(Deposit deposit);

    void doWithdraw(WithDraw withDraw);

    void doTransfer(Transfer transfer);
}
