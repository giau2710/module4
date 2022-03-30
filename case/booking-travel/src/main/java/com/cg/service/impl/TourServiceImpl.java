package com.cg.service.impl;

import com.cg.model.Tour;
import com.cg.repository.TourRepository;
import com.cg.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    @Autowired
    TourRepository tourRepository;

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
    public Tour getTourAddNew() {
        return tourRepository.getTourAddNew();
    }
}
