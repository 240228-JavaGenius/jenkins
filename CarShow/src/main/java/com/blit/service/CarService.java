package com.blit.service;

import java.util.List;

import com.blit.entity.Car;

public interface CarService {
	public List<Car> getCars();
	Car getCarById(Long id);
	Car saveCar(Car car);
	Car updateCar(Long id, Car car);
	void deleteCar(Long id);
}
