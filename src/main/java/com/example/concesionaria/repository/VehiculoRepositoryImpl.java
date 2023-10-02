package com.example.concesionaria.repository;

import com.example.concesionaria.entity.Vehiculo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VehiculoRepositoryImpl implements IvehiculoRepository{

    List<Vehiculo> lista = new ArrayList<>();

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {

        this.lista.add(vehiculo);
        return vehiculo;
    }

    @Override
    public List<Vehiculo> findAllSinServices() {
        return this.lista;
    }

    @Override
    public Vehiculo findVehiculoById(int id) {
        Vehiculo encontrado = null;
        for(Vehiculo v : lista){
            if(v.getId() == id){
                encontrado = v;
            }
        }
        return encontrado;
    }

    // Método con LocalDate
    @Override
    public List<Vehiculo> findVehiculosByDate(LocalDate date1, LocalDate date2) {
        return this.lista.stream()
                .filter(vehiculo -> vehiculo.getManufacturingDate()
                        .isAfter(date1) && vehiculo.getManufacturingDate().isBefore(date2))
                .toList();
    }

    /*
    // Método con Date

    @Override
    public List<Vehiculo> findVehiculosByDate(Date date1, Date date2) {
        return this.lista.stream()
                .filter(vehiculo -> vehiculo.getManufacturingDate()
                .after(date1) && vehiculo.getManufacturingDate().before(date2))
                .toList();
    }

    @Override
    public List<Vehiculo> findVehiculosByDate(String date) {
        return this.lista.stream().filter(vehiculo -> vehiculo.getManufacturingDate().equals(date)).toList();
    }
    */

    @Override
    public List<Vehiculo> findVehiculosByPrice(int price1, int price2) {
        return this.lista.stream()
                .filter(vehiculo -> vehiculo.getPrice() >= price1 && vehiculo.getPrice() <= price2)
                .toList();
    }

}
