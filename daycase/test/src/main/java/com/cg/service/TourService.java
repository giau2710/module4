package com.cg.service;

import com.cg.model.Tour;
import com.cg.model.User;

import java.util.List;

public interface TourService extends IGeneralService<Tour> {
    List<Tour> fillAllActive();
}
