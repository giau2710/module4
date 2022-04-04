package com.cg.service.impl;

import com.cg.model.ImageTour;
import com.cg.model.Tour;
import com.cg.repository.ImageTourRepository;
import com.cg.repository.TourRepository;
import com.cg.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    @Value("${file-upload}")
    private String fileUpload;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    ImageTourRepository imageTourRepository;

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public Tour getById(Long id) {
        return null;
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(Tour tour) {
        tour.setDeleted(true);
        tourRepository.save(tour);
    }

    @Override
    public List<Tour> findAllActive() {
        return tourRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Tour doTour(Tour tour, MultipartFile file) throws IOException {
        String fileNameImg = file.getOriginalFilename();
        Tour tourCreated = tourRepository.save(tour);
        ImageTour imageTour = new ImageTour(0L, fileNameImg, tourCreated);
        imageTourRepository.save(imageTour);
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(fileUpload, file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        return tourCreated;
    }

    @Override
    public Page<Tour> findAll(Pageable pageable) {
        return tourRepository.findAll(pageable);
    }


}
