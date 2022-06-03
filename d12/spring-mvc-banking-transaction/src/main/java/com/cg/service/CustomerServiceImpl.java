package com.cg.service;

import com.cg.model.*;
import com.cg.model.dto.CustomerDTO;
import com.cg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.getById(id);
    }

    @Override
    public List<CustomerDTO> fillAllActive() {
        return customerRepository.findAllCustomerDTOByDeletedIsFalse();
    }

    @Override
    public List<Customer> fillAllRecipient(Long senderId) {
        return customerRepository.findAllByIdNotAndDeletedIsFalse(senderId);
    }

    @Override
    public List<Transfer> fillAllTransfer() {
        return transferRepository.findAll();
    }

    @Override
    public Optional<CustomerDTO> findCustomerDTOById(Long id) {
        return customerRepository.findCustomerDTOById(id);
    }

    @Override
    public CustomerDTO getCustomerDTOById(Long id) {
        return customerRepository.getCustomerDTOById(id);
    }


    @Override
    public void incrementBalance(Customer customer, BigDecimal transactionAmount) {
        customerRepository.incrementBalance(customer.getId(), transactionAmount);
    }

    @Override
    public void reduceBalance(Customer customer, BigDecimal transactionAmount) {
        customerRepository.reduceBalance(customer.getId(), transactionAmount);
    }

    @Override
    public void doDeposit(Deposit deposit) {
        customerRepository.incrementBalance(deposit.getCustomer().getId(),deposit.getTransactionAmount());
        depositRepository.save(deposit);
    }

    @Override
    public void doWithdraw(WithDraw withDraw) {
        withdrawRepository.save(withDraw);
    }

    @Override
    public void doTransfer(Transfer transfer) {
        incrementBalance(transfer.getRecipient(),transfer.getTransferAmount());
        reduceBalance(transfer.getSender(),transfer.getTransactionAmount());
        transferRepository.save(transfer);
    }

    @Override
    public Customer save(Customer customer) {
       LocationRegion locationRegion= locationRegionRepository.save(customer.getLocationRegion());
        customer.setLocationRegion(locationRegion);
       return customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {

    }


    @Override
    public void softDelete(Customer customer) {
        customer.setDeleted(true);
        customerRepository.save(customer);
    }


}
