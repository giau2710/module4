package com.cg.model;

import com.cg.model.dto.LocationRegionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location_region")
@Accessors(chain = true)
public class LocationRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "ward_id")
    private String wardId;

    @Column(name = "ward_name")
    private String wardName;

    private String address;

    @OneToOne(mappedBy = "locationRegion")
    @JsonIgnore
    private Customer customer;

    @Column(name = "created_at",updatable = false)
    private Date createdAt = new Date();

    @Column(name = "created_by",updatable = false)
    private Long createdBy ;

    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @Column(name = "update_by")
    private Long updateBy ;


    public LocationRegionDTO toLocationRegionDTO() {
        LocationRegionDTO locationRegion = new LocationRegionDTO();
        locationRegion.setId(id);
        locationRegion.setProvinceId(provinceId);
        locationRegion.setProvinceName(provinceName);
        locationRegion.setDistrictId(districtId);
        locationRegion.setDistrictName(districtName);
        locationRegion.setWardId(wardId);
        locationRegion.setWardName(wardName);
        locationRegion.setAddress(address);
        return locationRegion;
    }
}
