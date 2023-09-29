package com.example.concesionaria.controller;

import com.example.concesionaria.dto.VehiculoDto;
import com.example.concesionaria.service.IvehiculoService;
import com.example.concesionaria.service.VehiculoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/vehicles")
public class controller {

    IvehiculoService service;

    public controller(VehiculoServiceImpl vehiculoService) {
        this.service = vehiculoService;
    }

    @PostMapping("/")
    public ResponseEntity<?> guardarVehiculo(@RequestBody VehiculoDto vehiculoDto){
        return new ResponseEntity<>(service.guardarVehiculo(vehiculoDto), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllSinServices(){
        return new ResponseEntity<>(service.findAllSinServices(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllVehiculoById(@PathVariable int id){
        return new ResponseEntity<>(service.findVehiculoById(id), HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<?> findVehiculoByDate(@RequestParam String since){
        return new ResponseEntity<>(service.findVehiculosByDate(since), HttpStatus.OK);
    }

    @GetMapping("/prices")
    public ResponseEntity<?> findVehiculoByPrices(@RequestParam int since){
        return new ResponseEntity<>(service.findVehiculosByPrice(since), HttpStatus.OK);
    }
}
