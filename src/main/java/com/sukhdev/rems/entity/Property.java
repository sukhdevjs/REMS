package com.sukhdev.rems.entity;

import com.sukhdev.rems.dto.PropertyDto;
import com.sukhdev.rems.enums.LocationType;
import com.sukhdev.rems.enums.PropertyType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;


    private String address;

    @Column(length = 1000)
    private String description;

    private Double size;

    private Integer numberOfRooms;

    private Integer price;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public PropertyDto getPropertyDto(){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setId(id);
        propertyDto.setName(name);
        propertyDto.setDescription(description);
        propertyDto.setPropertyType(propertyType);
        propertyDto.setLocationType(locationType);
        propertyDto.setAddress(address);
        propertyDto.setPrice(price);
        propertyDto.setReturnedImage(image);
        return propertyDto;
    }


}
