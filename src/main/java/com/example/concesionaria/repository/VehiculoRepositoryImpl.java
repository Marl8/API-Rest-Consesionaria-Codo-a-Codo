package com.example.concesionaria.repository;

import com.example.concesionaria.entity.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehiculoRepositoryImpl implements IvehiculoRepository{

    List<Vehiculo> lista = new ArrayList<>();

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {

        lista.add(vehiculo);
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

    @Override
    public List<Vehiculo> findVehiculosByDate(String date) {
        return this.lista.stream().filter(vehiculo -> vehiculo.getManufacturingDate().equals(date)).toList();
    }

    @Override
    public List<Vehiculo> findVehiculosByPrice(int price) {
        return this.lista.stream().filter(vehiculo -> vehiculo.getPrice() == price).toList();
    }
}
