package com.cg.repository;

import com.cg.model.Tour;
import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour,Long> {
    List<Tour> findAllByDeletedIsFalse();
}
