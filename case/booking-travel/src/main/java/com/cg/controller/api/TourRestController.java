package com.cg.controller.api;

import com.cg.model.Tour;
import com.cg.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/tours")
public class TourRestController {



    @Autowired
    TourService tourService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            return new ResponseEntity<>(tour.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for get tour", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody Tour tour) {

        tourService.save(tour);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody Tour tour) {
        Optional<Tour> tourCheck = tourService.findById(id);
        if (tourCheck.isPresent()) {
            tourService.save(tour);
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for get customer", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<?> doDelete(@PathVariable Long tourId) {
        Optional<Tour> tour = tourService.findById(tourId);
        if (tour.isPresent()) {
            tourService.softDelete(tour.get());
            return new ResponseEntity<>("Delete tour successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error for delete customer", HttpStatus.BAD_REQUEST);
    }
}
