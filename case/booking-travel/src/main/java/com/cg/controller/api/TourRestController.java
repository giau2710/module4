package com.cg.controller.api;

import com.cg.model.ImageTour;
import com.cg.model.Tour;
import com.cg.model.dto.TourDTO;
import com.cg.model.form.TourForm;
import com.cg.service.ImageTourService;
import com.cg.service.TourService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tours")
public class TourRestController {

    @Autowired
    AppUtils appUtils;

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    TourService tourService;

    @Autowired
    ImageTourService imageTourService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Tour> tour = tourService.findById(id);
        if (tour.isPresent()) {
            return new ResponseEntity<>(tour.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error for get tour", HttpStatus.NO_CONTENT);
        }
    }

//    @GetMapping
//    public ResponseEntity<?> getAllTour(@PageableDefault(value = 5) Pageable pageable) {
//        Page<Tour> tourPage = tourService.findAll(pageable);
//        List<Tour> tourList = tourPage.getContent();
//        return new ResponseEntity<>(tourList, HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<?> getAllTour() {
        List<Tour> list = tourService.findAllActive();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> createAdmin(@RequestBody TourDTO tourDTO) {
//
//        tourService.save(tourDTO.toTour());
//        return new ResponseEntity<>(tourDTO.toTour(), HttpStatus.OK);
//    }


    @PostMapping("/save")
    public ResponseEntity<?> saveTour(@Validated TourForm tourForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        TourDTO tourDTO = new TourDTO(0L, tourForm.getName(), tourForm.getStartDay(), tourForm.getEndDay(), tourForm.getDeparture(), tourForm.getDestination(), tourForm.getPrice(), tourForm.getDetails());
        MultipartFile file = tourForm.getImage();

        try {
            Tour tour = tourService.doTour(tourDTO.toTour(), file);
            return new ResponseEntity<>(tour, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Tour Add Error.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTour(@PathVariable Long id, @Validated @RequestBody TourDTO tourDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        Optional<Tour> tourCheck = tourService.findById(id);
        if (tourCheck.isPresent()) {
            Tour tourUpdated = tourService.save(tourDTO.toTour());
            return new ResponseEntity<>(tourUpdated.toTourDTO(), HttpStatus.OK);
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
