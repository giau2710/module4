package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllByDeletedIsFalse();

    @Query("SELECT new com.cg.model.dto.CustomerDTO(" +
            "c.id," +
            "c.fullName," +
            "c.email," +
            "c.phone," +
            "c.balance," +
            "c.locationRegion" +
            ") " +
            "FROM Customer AS c " +
            "WHERE c.deleted=false "
    )
    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse();

    @Query("SELECT new com.cg.model.dto.CustomerDTO(" +
            "c.id," +
            "c.fullName," +
            "c.email," +
            "c.phone," +
            "c.balance," +
            "c.locationRegion" +
            ") " +
            "FROM Customer AS c " +
            "WHERE c.id = :id " +
            "AND c.deleted=false "
    )
    Optional<CustomerDTO> findCustomerDTOById(@Param("id") Long id);

    @Query("SELECT new com.cg.model.dto.CustomerDTO(" +
            "c.id," +
            "c.fullName," +
            "c.email," +
            "c.phone," +
            "c.balance," +
            "c.locationRegion" +
            ") " +
            "FROM Customer AS c " +
            "WHERE c.id = :id "+
            "AND c.deleted=false "
    )
    CustomerDTO getCustomerDTOById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Customer c SET c.balance= c.balance + :transactionAmount WHERE c.id = :customerId")
    void incrementBalance(@Param("customerId") Long customerId, @Param("transactionAmount") BigDecimal transactionAmount);

    @Modifying
    @Query("UPDATE Customer c SET c.balance= c.balance - :transactionAmount WHERE c.id = :customerId")
    void reduceBalance(@Param("customerId") Long customerId, @Param("transactionAmount") BigDecimal transactionAmount);

    List<Customer> findAllByIdNotAndDeletedIsFalse(Long id);

}
