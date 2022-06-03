package com.cg.model.dto;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositDTO {


    private long id;
    private CustomerDTO customer;

    @NotNull(message = "The transaction amount is required")
    @DecimalMin(value = "49",message = "Transaction amount must be greater than or equal to 50",inclusive = false)
    @DecimalMax(value = "1000000000",message = "Transaction amount must be less than or equal to 1.000.000.000",inclusive = true)
    private BigDecimal transactionAmount;

    public Deposit toDeposit() {
        return new Deposit()
                .setId(id)
                .setCustomer(customer.toCustomer())
                .setTransactionAmount(transactionAmount);
    }


}
