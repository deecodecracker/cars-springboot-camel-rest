package com.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cars.entity.Car;
import com.cars.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private final CarRepository carRepository;

	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public List<Car> findAllCars() {
		return carRepository.findAll();
	}

	public Car findCarById(Integer carId) {
		return carRepository.findCarById(carId);
	}

	public Car addCar(Car car) {
		return carRepository.save(car);
	}

	public Car updateCar(Integer carId, Car car) {
		Car carToBeUpdated = findCarById(carId);
		carToBeUpdated.setName(car.getName());
		carToBeUpdated.setBrand(car.getBrand());
		carToBeUpdated.setPrice(car.getPrice());
		return carRepository.save(carToBeUpdated);
	}

	public void removeCar(Integer carId) {
		carRepository.deleteById(carId);
	}
}
