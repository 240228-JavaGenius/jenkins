package com.blit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blit.entity.Car;
import com.blit.service.CarService;

@RestController
@ResponseBody
@RequestMapping("/api/v1/car")
@CrossOrigin("http://localhost:3000")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/all")
	public ResponseEntity<List<Car>> getCars() {
		return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> getCar(@PathVariable Long id) {
		return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Car> saveCar(@RequestBody Car car){
	      return  new ResponseEntity<>(carService.saveCar(car),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car){
	      return new ResponseEntity<>(carService.updateCar(id,car),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
