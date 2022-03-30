package com.cg.repository;

import com.cg.model.Tour;
import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    List<Tour> findAllByDeletedIsFalse();

    @Query("SELECT t FROM Tour AS t  WHERE t.id = (SELECT MAX(t.id) FROM Tour AS t)")
    Tour getTourAddNew();
}
