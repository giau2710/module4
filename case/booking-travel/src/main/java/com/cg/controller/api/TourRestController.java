package com.cg.controller.api;

import com.cg.model.ImageTour;
import com.cg.model.Tour;
import com.cg.model.form.TourForm;
import com.cg.service.ImageTourService;
import com.cg.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/tours")
public class TourRestController {


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

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody Tour tour) throws IOException {

        tourService.save(tour);

        return new ResponseEntity<>(tour, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<Object> saveTour(TourForm tourForm)
            throws IOException {
        Tour tour = new Tour(0L, tourForm.getName(), tourForm.getStartDay(), tourForm.getEndDay(), tourForm.getDeparture(), tourForm.getDestination(), tourForm.getPrice(), tourForm.getDetails(), false);
        tourService.save(tour);

        MultipartFile file = tourForm.getAvatar();
        String fileName = file.getOriginalFilename();
        Tour tourNew = tourService.getTourAddNew();
        ImageTour imageTour = new ImageTour(0L, fileName, tourNew);
        imageTourService.save(imageTour);
        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(fileUpload, file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } else {
            return new ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Tour Add Successfully.", HttpStatus.OK);
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
