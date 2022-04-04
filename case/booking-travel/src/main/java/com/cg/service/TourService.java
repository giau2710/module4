package com.cg.service;

import com.cg.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TourService extends IGeneralService<Tour> {

    List<Tour> findAllActive();

    Tour doTour(Tour tour, MultipartFile file) throws IOException;

    Page<Tour> findAll(Pageable pageable);
}
