package com.cg.repository;

import com.cg.model.WithDraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<WithDraw,Long> {
}
