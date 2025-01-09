package com.sukhdev.rems.dto;

import com.sukhdev.rems.enums.LocationType;
import com.sukhdev.rems.enums.PropertyType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@Data
public class PropertyDto {
    private Long id;


    private String name;


    private PropertyType propertyType;


    private LocationType locationType;


    private String address;


    private String description;

    private Double size;

    private Integer numberOfRooms;


    private Integer price;

    private MultipartFile image;

    private byte[] returnedImage;

}
