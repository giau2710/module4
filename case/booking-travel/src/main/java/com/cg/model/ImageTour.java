package com.cg.model;

import javax.persistence.*;

@Entity
@Table(name ="image_tour")
public class ImageTour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String img;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    public ImageTour() {
    }

    public ImageTour(Long id, String img, Tour tour) {
        this.id = id;
        this.img = img;
        this.tour = tour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
