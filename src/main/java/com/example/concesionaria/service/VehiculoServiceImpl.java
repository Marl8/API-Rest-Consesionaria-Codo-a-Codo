package com.example.concesionaria.service;

import com.example.concesionaria.dto.*;
import com.example.concesionaria.entity.Vehiculo;
import com.example.concesionaria.repository.IvehiculoRepository;
import com.example.concesionaria.repository.VehiculoRepositoryImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<VehiculoGetDto> findVehiculosByDate(String date1, String date2) {
        Date fecha1 = this.convertirFecha(date1);
        Date fecha2 = this.convertirFecha(date2);
        List<Vehiculo> result = repository.findVehiculosByDate(fecha1, fecha2);
        return convertirDto(result);
    }

    @Override
    public List<VehiculoGetDto> findVehiculosByPrice(int price1, int price2) {
        List<Vehiculo> result = repository.findVehiculosByPrice(price1, price2);
        return convertirDto(result);
    }

    /*
    // Método lambda para convertir a dto usando Foreach()

    private List<VehiculoGetDto> convertirDto(List<Vehiculo> lista){
        List<VehiculoGetDto> listaResponse = new ArrayList<>();
        lista.stream().forEach(v ->{
            listaResponse.add(new VehiculoGetDto(v.getId(), v.getBrand(),v.getModel(), v.getManufacturingDate(),
                    v.getNumberOfKilometers(), v.getDoors(), v.getPrice(), v.getCurrency(),
                    v.getCountOfOwners()));
        });
        return listaResponse;
    }*/

    // Método lambda para convertir a dto usando Map()
    private List<VehiculoGetDto> convertirDto(List<Vehiculo> lista){
        List<VehiculoGetDto> listaResponse = lista.stream().map(v -> new VehiculoGetDto(v.getId(), v.getBrand(),
                v.getModel(), v.getManufacturingDate(), v.getNumberOfKilometers(), v.getDoors(), v.getPrice(),
                v.getCurrency(), v.getCountOfOwners())).toList();
        return listaResponse;
    }

    private Date convertirFecha(String date) {
        // Se da formato a la fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            // Se parsea el parámetro de tipo String a tipo Date
            fecha = formato.parse(date);
        } catch (ParseException e) {
            System.out.println("Error parsing date" + e.getMessage());
        }
        return fecha;
    }
}
