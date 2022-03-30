package com.cg.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourForm {
    private Long id;

    private String name;

    private Date startDay;

    private Date endDay;

    private String departure;

    private String destination;

    private BigDecimal price;

    private String details;

    private MultipartFile avatar;

    private boolean deleted;

}
