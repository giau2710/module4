package com.cg.controller.rest;

import com.cg.model.*;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.dto.DepositDTO;
import com.cg.repository.TransferRepository;
import com.cg.service.CustomerService;
import com.cg.service.TransferService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Binding;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    @Autowired
    TransferService transferService;

    @Autowired
    AppUtils appUtils;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<CustomerDTO> customerDTO = customerService.findCustomerDTOById(id);
        if (customerDTO.isPresent()) {
            return new ResponseEntity<>(customerDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for get customer", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<CustomerDTO> list = customerService.fillAllActive();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/get-all-recipient-without-sender/{senderId}")
    public ResponseEntity<?> getAllRecipient(@PathVariable Long senderId) {
        Optional<Customer> sender = customerService.findById(senderId);
        List<Customer> recipients = customerService.fillAllRecipient(senderId);
        if (sender.isPresent()) {
            return new ResponseEntity<>(recipients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/transfer-history")
    public ModelAndView transferHistory() {
        List<Transfer> transfers = customerService.fillAllTransfer();
        ModelAndView modelAndView = new ModelAndView("/customer/transferHistory");
        BigDecimal totalFeesAmount = transferService.totalFessAmount();
        modelAndView.addObject("totalFeesAmount", totalFeesAmount);
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        customerDTO.setBalance(BigDecimal.valueOf(0L));
        Customer customerCreated = customerService.save(customerDTO.toCustomer());
        customerDTO = customerCreated.toCustomerDTO();
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> doTransfer(@RequestBody Transfer transfer) {
        Optional<Customer> sender = customerService.findById(transfer.getSender().getId());
        if (sender.isPresent()) {
            Optional<Customer> recipient = customerService.findById(transfer.getRecipient().getId());
            if (recipient.isPresent() && sender.get().getId() != recipient.get().getId()) {
                BigDecimal senderBalance = sender.get().getBalance();
                BigDecimal recipientBalance = recipient.get().getBalance();

                BigDecimal transferAmount = transfer.getTransferAmount();
                int fess = 10;
                BigDecimal feesAmount = transferAmount.multiply(BigDecimal.valueOf(fess)).divide(BigDecimal.valueOf(100));
                BigDecimal transactionAmount = transferAmount.add(feesAmount);
                if (senderBalance.compareTo(transactionAmount) >= 0) {
                    try {
//                        sender.get().setBalance(senderBalance.subtract(transactionAmount));
//                        recipient.get().setBalance(recipientBalance.add(transferAmount));

                        transfer.setSender(sender.get());
                        transfer.setRecipient(recipient.get());
                        transfer.setTransferAmount(transferAmount);
                        transfer.setFees(fess);
                        transfer.setFeesAmount(feesAmount);
                        transfer.setTransactionAmount(transactionAmount);

                        customerService.doTransfer(transfer);

                        CustomerDTO senderSuccess = customerService.getCustomerDTOById(sender.get().getId());
                        CustomerDTO recipientSuccess = customerService.getCustomerDTOById(recipient.get().getId());

                        Map<String, CustomerDTO> customers = new HashMap<>();
                        customers.put("sender", senderSuccess);
                        customers.put("recipient", recipientSuccess);
                        return new ResponseEntity<>(customers, HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>("Information transfer invalid, please contact administrator", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("Sender balance not enough transfer", HttpStatus.BAD_REQUEST);
                }

            } else {
                return new ResponseEntity<>("Recipient information invalid", HttpStatus.NOT_FOUND);
            }

        } else {
            return new ResponseEntity<>("Sender information invalid", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()) {
            customer.setId(customerOptional.get().getId());
            customerService.save(customer);
            return new ResponseEntity<>(customer.toCustomerDTO(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error for update customer", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@Validated @RequestBody DepositDTO depositDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
//        customerService.incrementBalance(deposit.getCustomer(), deposit.getTransactionAmount());
        customerService.doDeposit(depositDTO.toDeposit());
//        Optional<Customer> customer = customerService.findById(deposit.getCustomer().getId());
        Optional<CustomerDTO> customerDTO = customerService.findCustomerDTOById(depositDTO.getCustomer().getId());
        return new ResponseEntity<>(customerDTO.get(), HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody WithDraw withDraw) {
        customerService.reduceBalance(withDraw.getCustomer(), withDraw.getTransactionAmount());
        customerService.doWithdraw(withDraw);
        Optional<Customer> customer = customerService.findById(withDraw.getCustomer().getId());
        return new ResponseEntity<>(customer.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<?> doDelete(@PathVariable Long customerId) {
        Optional<Customer> customer = customerService.findById(customerId);
        if (customer.isPresent()) {
            customerService.softDelete(customer.get());
            return new ResponseEntity<>("Delete customer successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error for delete customer", HttpStatus.BAD_REQUEST);
    }
}
