package com.example.concesionaria.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehiculo {

    private int id;
    private String brand;
    private String model;
    private Date manufacturingDate;
    private int numberOfKilometers;
    private int doors;
    private int price;
    private String currency;
    List<Service> services;
    private int countOfOwners;
}
