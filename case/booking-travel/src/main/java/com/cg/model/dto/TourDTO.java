package com.cg.model.dto;


import com.cg.model.Tour;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class TourDTO {
    private Long id;

    @NotNull(message = "The name  is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "The startDay  is required")
    private Date startDay;

    @NotNull(message = "The endDay  is required")
    private Date endDay;

    @NotNull(message = "The departure  is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String departure;

    @NotNull(message = "The destination  is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String destination;

    @NotNull(message = "The price  is required")
    @DecimalMin(value = "99999",message = "Price must be greater than or equal to 100.000",inclusive = false)
    private BigDecimal price;

    private String details;

    public Tour toTour() {
        return new Tour()
                .setId(id)
                .setName(name)
                .setStartDay(startDay)
                .setEndDay(endDay)
                .setDeparture(departure)
                .setDestination(destination)
                .setPrice(price)
                .setDetails(details);
    }
}
