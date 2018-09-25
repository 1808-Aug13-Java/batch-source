package com.revature.services;
import java.util.List;
import javax.jws.WebService;
import com.revature.models.Car;
@WebService
public interface Garage {
    
    public List<Car> getAllCars();
    public String addCar(Car car);
}