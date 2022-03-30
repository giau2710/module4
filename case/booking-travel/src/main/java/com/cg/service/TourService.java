package com.cg.service;

import com.cg.model.Tour;

import java.util.List;

public interface TourService extends IGeneralService<Tour> {

    List<Tour> findAllActive();

    Tour getTourAddNew();
}
