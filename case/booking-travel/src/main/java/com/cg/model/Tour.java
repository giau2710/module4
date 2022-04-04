package com.cg.model;

import com.cg.model.dto.TourDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "start_day")
    private Date startDay;

    @Column(name = "end_day")
    private Date endDay;

    private String departure;

    private String destination;

    private BigDecimal price;

    private String details;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ImageTour> imageTours;

    private boolean deleted;

    public TourDTO toTourDTO(){
        return new TourDTO()
                .setId(id)
                .setName(name)
                .setStartDay(startDay)
                .setEndDay(endDay)
                .setDeparture(departure)
                .setDestination(destination)
                .setPrice(price)
                .setDetails(details);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", details='" + details + '\'' +
                ", imageTours=" + imageTours +
                ", deleted=" + deleted +
                '}';
    }
}
