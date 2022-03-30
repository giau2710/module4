package com.cg.service.impl;

import com.cg.model.ImageTour;
import com.cg.repository.ImageTourRepository;
import com.cg.service.ImageTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageTourServiceImpl implements ImageTourService {

    @Autowired
    ImageTourRepository imageTourRepository;

    @Override
    public List<ImageTour> findAll() {
        return imageTourRepository.findAll();
    }

    @Override
    public Optional<ImageTour> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ImageTour getById(Long id) {
        return null;
    }

    @Override
    public ImageTour save(ImageTour imageTour) {
        return imageTourRepository.save(imageTour);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void softDelete(ImageTour imageTour) {

    }
}
