package Programming.FourthSemLab.FourthLab.cars;

import static Programming.FourthSemLab.FourthLab.cars.Car.CarType.PASSENGER;
import static Programming.FourthSemLab.FourthLab.cars.Car.CarType.TRUCK;

import Programming.FourthSemLab.FourthLab.cars.Car.CarType;

public class CarFactory {

  public static Car createCar(CarType type, String brand, String color, Engine engine,
      Integer numOfWheels) {
    return switch (type) {
      case BUS -> new Bus(brand, color, engine, numOfWheels);
      case TRUCK -> new Truck(brand, color, engine, numOfWheels);
      case PASSENGER -> new Passenger(brand, color, engine, numOfWheels);
    };
  }
}
