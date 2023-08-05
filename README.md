# cars-springboot-camel-rest
Car API using Springboot and Camel Rest 

POST : http://localhost:8282/rest/car

Sample input json 

{
	"name": "Audi A3",
	"brand": "Audi",
	"price": 300
}

GET (all cars) : http://localhost:8282/rest/car

GET (by id) : http://localhost:8282/rest/car/1

PUT : http://localhost:8282/rest/car/1

{
    "name": "Audi A8",
    "brand": "Audi",
    "price": 800
}

DELETE (by id) : http://localhost:8282/rest/car/4
