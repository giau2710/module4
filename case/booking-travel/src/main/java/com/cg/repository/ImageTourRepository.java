package com.cg.repository;

import com.cg.model.ImageTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageTourRepository extends JpaRepository<ImageTour,Long> {

}
