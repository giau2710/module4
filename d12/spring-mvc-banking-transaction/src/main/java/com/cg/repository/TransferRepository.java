package com.cg.repository;

import com.cg.model.Transfer;
import com.cg.model.dto.TransferDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query("SELECT new com.cg.model.dto.TransferDTO(" +
            "t.id," +
            "t.sender.id," +
            "t.sender.fullName," +
            "t.recipient.id," +
            "t.recipient.fullName," +
            "t.transferAmount," +
            "t.fees," +
            "t.feesAmount," +
            "t.transactionAmount" +
            ") " +
            "FROM Transfer AS t")
    List<TransferDTO> fillAllTransferDTO();

    @Query(value = "SELECT sum(fess_amount)  FROM customer.transfer",nativeQuery = true)
    BigDecimal totalFessAmount();

}
