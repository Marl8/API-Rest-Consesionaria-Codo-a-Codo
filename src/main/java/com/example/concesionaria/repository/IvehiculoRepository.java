package com.example.concesionaria.repository;

import com.example.concesionaria.dto.VehiculoDto;
import com.example.concesionaria.entity.Vehiculo;

import java.util.List;

public interface IvehiculoRepository {

    public Vehiculo guardarVehiculo(Vehiculo vehiculo);

    public List<Vehiculo> findAllSinServices();

    public Vehiculo findVehiculoById(int id);

    public List<Vehiculo> findVehiculosByDate(String date);

    public List<Vehiculo> findVehiculosByPrice(int price);
}
