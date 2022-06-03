package com.cg.model.dto;

import com.cg.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class LocationRegionDTO {

    private long id;
    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;

    @NotBlank(message = "The address is required")
    private String address;

    public LocationRegion toLocationRegion() {
        LocationRegion locationRegion = new LocationRegion();
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
