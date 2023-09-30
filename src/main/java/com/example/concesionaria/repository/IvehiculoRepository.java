package com.example.concesionaria.repository;

import com.example.concesionaria.entity.Vehiculo;
import java.util.Date;
import java.util.List;

public interface IvehiculoRepository {

    public Vehiculo guardarVehiculo(Vehiculo vehiculo);

    public List<Vehiculo> findAllSinServices();

    public Vehiculo findVehiculoById(int id);

    public List<Vehiculo> findVehiculosByDate(Date date1, Date date2);

    public List<Vehiculo> findVehiculosByPrice(int price1, int price2);
}
