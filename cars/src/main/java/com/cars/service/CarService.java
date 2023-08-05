package com.cars.service;

import java.util.List;

import com.cars.entity.Car;

public interface CarService {

	public List<Car> findAllCars();

	public Car findCarById(Integer carId);

	public Car addCar(Car car);

	public Car updateCar(Integer carId, Car car);

	public void removeCar(Integer carId);

}
