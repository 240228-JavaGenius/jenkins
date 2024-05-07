package com.blit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.blit.entity.Car;
import com.blit.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	private CarRepository carRepository;

	@Override
	public List<Car> getCars() {
		return (List<Car>) carRepository.findAll();// find all return itteralbe so we need to cast it to List type.
	}

	@SuppressWarnings("deprecation")
	@Override
	public Car getCarById(Long id) {
		Optional<Car> car = carRepository.findById(id);
		if (car.isPresent()) {
			return car.get();
		} else {
			throw new ResourceNotFoundException("Oops! Car with " + id + " does not exits in our database");
		}
	}
	
	@Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
	
	@Override
    public Car updateCar(Long id, Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()){
            optionalCar.get().setBrand(car.getBrand());
            optionalCar.get().setModel(car.getModel());
            optionalCar.get().setColor(car.getColor());
            optionalCar.get().setPrice(car.getPrice());
            optionalCar.get().setYear(car.getYear());
            optionalCar.get().setOwner(car.getOwner());
            System.out.println(optionalCar.get().getBrand());
            carRepository.save(optionalCar.get());
              
            return optionalCar.get();
        } else {
            throw new ResourceNotFoundException("Car with"+id+" does not exits");
        }
       
	}
	
	 @Override
     public void deleteCar(Long id) {
         carRepository.deleteById(id);
     }
	
}