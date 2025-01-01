package com.sukhdev.rems.entity;

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

    private String type;

    private String address;

    @Column(length = 1000)
    private String description;

    private Double size;

    private Integer numberOfRooms;

    private Integer price;

    @Column(columnDefinition = "longblob")
    private byte[] image;


}
