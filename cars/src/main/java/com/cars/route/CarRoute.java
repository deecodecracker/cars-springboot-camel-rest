package com.cars.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.cars.entity.Car;
import com.cars.service.CarServiceImpl;

@Component
public class CarRoute extends RouteBuilder {

	private final Environment env;

	public CarRoute(Environment env) {
		this.env = env;
	}

	public void configure() throws Exception {

		restConfiguration().contextPath(env.getProperty("camel.component.servlet.mapping.contextPath", "/rest/*"))
				.port(env.getProperty("server.port", "8282")).bindingMode(RestBindingMode.json);

		rest("/car").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
				.get("/").route().to("{{route.findAllCars}}").endRest()
				.get("/{carId}").route().to("{{route.findCarById}}").endRest()
				.post("/").route().marshal().json()
				.unmarshal(getJacksonDataFormat(Car.class)).to("{{route.saveCar}}").endRest()
				.put("/{carId}").route().marshal().json()
				.unmarshal(getJacksonDataFormat(Car.class)).to("{{route.updateCar}}").endRest()
				.delete("/{carId}").route().to("{{route.removeCar}}").end();
		
		from("{{route.findAllCars}}").bean(CarServiceImpl.class, "findAllCars");
		
		from("{{route.findCarById}}").log("Received header : ${header.carId}").bean(CarServiceImpl.class,
				"findCarById(${header.carId})");

		from("{{route.saveCar}}").log("Received Body ${body}").bean(CarServiceImpl.class, "addCar(${body})");
		
		from("{{route.updateCar}}").log("Received Body ${body}").bean(CarServiceImpl.class, "updateCar(${header.carId},${body})");

		from("{{route.removeCar}}").log("Received header : ${header.carId}").bean(CarServiceImpl.class,
				"removeCar(${header.carId})");
	}

	private JacksonDataFormat getJacksonDataFormat(Class<?> unmarshalType) {
		JacksonDataFormat format = new JacksonDataFormat();
		format.setUnmarshalType(unmarshalType);
		return format;
	}
}
