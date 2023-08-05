package com.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cars.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
	
	Car findCarById(Integer carId);
}
