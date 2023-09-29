package com.example.concesionaria.service;

import com.example.concesionaria.dto.VehiculoDto;
import com.example.concesionaria.dto.VehiculoGetDto;
import com.example.concesionaria.dto.VehiculoResponseDto;
import com.example.concesionaria.entity.Vehiculo;
import com.example.concesionaria.repository.IvehiculoRepository;
import com.example.concesionaria.repository.VehiculoRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoServiceImpl implements IvehiculoService{

    IvehiculoRepository repository;

    public VehiculoServiceImpl(VehiculoRepositoryImpl vehiculoRepository) {
        this.repository = vehiculoRepository;
    }

    @Override
    public VehiculoResponseDto guardarVehiculo(VehiculoDto auto) {
        ObjectMapper mapper = new ObjectMapper();
        Vehiculo vehiculo = mapper.convertValue(auto,Vehiculo.class);
        Vehiculo respuestaRepo = repository.guardarVehiculo(vehiculo);

        if(respuestaRepo == null){
            return new VehiculoResponseDto("No se logró guardar.");
        }
        return new VehiculoResponseDto("El vehiculo modelo "+ respuestaRepo.getModel() + " se guardó correctamente.");
    }

    @Override
    public List<VehiculoGetDto> findAllSinServices() {
        List<Vehiculo> result = repository.findAllSinServices();
        return convertirDto(result);
    }

    @Override
    public VehiculoDto findVehiculoById(int id) {
        ObjectMapper mapper = new ObjectMapper();
        Vehiculo auto = repository.findVehiculoById(id);
        return mapper.convertValue(auto,VehiculoDto.class);
    }

    @Override
    public List<VehiculoGetDto> findVehiculosByDate(String date) {
        List<Vehiculo> result = repository.findVehiculosByDate(date);
        List<VehiculoGetDto> listaResponse = new ArrayList<>();
        return convertirDto(result);
    }

    @Override
    public List<VehiculoGetDto> findVehiculosByPrice(int price) {
        List<Vehiculo> result = repository.findVehiculosByPrice(price);
        return convertirDto(result);
    }

    private List<VehiculoGetDto> convertirDto(List<Vehiculo> lista){
        List<VehiculoGetDto> listaResponse = new ArrayList<>();
        lista.stream().forEach(v ->{
            listaResponse.add(new VehiculoGetDto(v.getId(), v.getBrand(),v.getModel(), v.getManufacturingDate(),
                    v.getNumberOfKilometers(), v.getDoors(), v.getPrice(), v.getCurrency(),
                    v.getCountOfOwners()));
        });
        return listaResponse;
    }
}
