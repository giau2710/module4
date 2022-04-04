package com.cg.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourForm {

    private Long id;

    @NotNull(message = "The name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "The startDay is required")
    private Date startDay;

    @NotNull(message = "The endDay is required")
    private Date endDay;

    @Size(min = 2, max = 30)
    private String departure;

    @Size(min = 2, max = 30)
    private String destination;

    @NotNull(message = "The price  is required")
    @DecimalMin(value = "99999",message = "Price must be greater than or equal to 100.000",inclusive = false)
    private BigDecimal price;

    @Size(min = 2)
    private String details;

    @NotNull(message = "The image is required")
    private MultipartFile image;

    private boolean deleted;

}
