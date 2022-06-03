package com.cg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "deposits")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @NotNull(message = "The transaction amount is required")
    @DecimalMin(value = "49",message = "Transaction amount must be greater than or equal to 50",inclusive = false)
    @DecimalMax(value = "1000000000",message = "Transaction amount must be less than or equal to 1.000.000.000",inclusive = true)
    @Digits(integer = 14, fraction = 0)
    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "created_at",updatable = false)
    private Date createdAt = new Date();

    @Column(name = "created_by",updatable = false)
    private Long createdBy ;

    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @Column(name = "update_by")
    private Long updateBy ;


}
