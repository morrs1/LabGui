package Programming.FourthSemLab.FourthLab.cars;

import java.util.ArrayList;

public class CarBase {
    ArrayList<Car> carsParked;
    ArrayList<Car> carsInTransit;
    ArrayList<Car> carsInRepair;

    public CarBase(Integer maxAmountOfCars){
        carsParked = new ArrayList<>(maxAmountOfCars);
        carsInRepair = new ArrayList<>(maxAmountOfCars);
        carsInTransit = new ArrayList<>(maxAmountOfCars);
    }

}
