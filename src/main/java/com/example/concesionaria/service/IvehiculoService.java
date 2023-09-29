package com.example.concesionaria.service;

import com.example.concesionaria.dto.VehiculoDto;
import com.example.concesionaria.dto.VehiculoGetDto;
import com.example.concesionaria.dto.VehiculoResponseDto;
import com.example.concesionaria.entity.Vehiculo;

import java.util.List;

public interface IvehiculoService {

    public VehiculoResponseDto guardarVehiculo(VehiculoDto auto);

    public List<VehiculoGetDto> findAllSinServices();

    public VehiculoDto findVehiculoById(int id);

    public List<VehiculoGetDto> findVehiculosByDate(String date);

    public List<VehiculoGetDto> findVehiculosByPrice(int price);
}
