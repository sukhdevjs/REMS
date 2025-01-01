package com.sukhdev.rems.dto;

import jakarta.persistence.Column;
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

    private String type;

    private String address;

    @Column(length = 1000)
    private String description;

    private Double size;

    private Integer numberOfRooms;

    private Integer price;

    private MultipartFile image;

    private byte[] returnedImage;

}
