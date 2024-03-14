package Programming.FourthSemLab.FourthLab;

import Programming.FourthSemLab.FourthLab.cars.Bus;
import Programming.FourthSemLab.FourthLab.cars.Car;
import Programming.FourthSemLab.FourthLab.cars.Car.CarType;
import Programming.FourthSemLab.FourthLab.cars.CarBase;
import Programming.FourthSemLab.FourthLab.cars.CarFactory;
import Programming.FourthSemLab.FourthLab.cars.Engine;
import Programming.FourthSemLab.FourthLab.cars.Engine.TypeOfFuel;
import Programming.FourthSemLab.FourthLab.cars.Passenger;
import Programming.FourthSemLab.FourthLab.cars.SpecialCar;
import Programming.FourthSemLab.FourthLab.cars.Truck;

public class Main {

  public static void main(String[] args) {
    System.out.println(seventhTask(" "));
  }

  public static String firstTask(String ignoredUnused) {
    var res = new StringBuilder();
    Car car = new Car("Audi", Car.CarType.PASSENGER, "blue",
        new Engine("11233", 200.0, 400.0, TypeOfFuel.DIESEL, 10.5, 16), 4);
    res.append(car.getBrand()).append("\n").append(car.getType()).append("\n")
        .append(car.getColor())
        .append("\n").append(car.getEngine()).append("\n").append(car.getAmountOfWheels())
        .append("\n");
    res.append(car.getRegPlate()).append("\n");
    car.setRegPlate("А123ВС77RUS");
    res.append(car.getRegPlate()).append("\n");
    car.setColor("Blue");
    res.append(car.getColor());
    car.setRegPlate("123ВС77RUS");
    return res.toString();
  }

  public static String secondTask(String ignoredUnused) {
    return " fdfd";
  }

  public static String thirdTask(String ignoredUnused) {

    return "";
  }

  public static String fourthTask(String ignoredUnused) {
    Car car = new Car("Audi", Car.CarType.PASSENGER, "blue",
        new Engine("11233", 200.0, 400.0, TypeOfFuel.DIESEL, 10.5, 16), 4);

    return car.getEngine().serialNumber() + " " + car.getEngine().typeOfFuel() + " "
        + car.getEngine().capacity() + "\n\n"
        + car;
  }

  public static String fifthTask(String ignoredUnused) {
    Bus bus = new Bus("Mercedes", "blue",
        new Engine("11233", 200.0, 400.0, TypeOfFuel.DIESEL, 10.5, 16), 4);
    Passenger passenger = new Passenger("BMW", "black",
        new Engine("11233", 200.0, 400.0, TypeOfFuel.DIESEL, 10.5, 16), 4);
    Truck truck = new Truck("Land Rover", "pink",
        new Engine("11233", 2000.0, 400.0, TypeOfFuel.DIESEL, 10.5, 16), 4);
    SpecialCar specialCar = new SpecialCar("Lada", "gray",
        new Engine("1178233", 20000.0, 4000.0, TypeOfFuel.DIESEL, 100.5, 32), 6);
    truck.setRegPlate("А123АА177RUS");
    specialCar.setRegPlate("1323АВ177RUS");
    return bus + "\n\n" + passenger + "\n\n" + truck + "\n\n" + specialCar;
  }


  public static String sixthTask(String ignoredUnused) {
    return "Не знаю, что здесь тестить, тк изменения были чисто внутренними";
  }


  public static String seventhTask(String ignoredUnused) {
    var res = new StringBuilder();
    var base = new CarBase(5);
    var car1 = CarFactory.createCar(CarType.PASSENGER, "Audi", "blue",
        new Engine("1000", 20.0, 100.0, TypeOfFuel.DIESEL, 3.0, 16), 4);
    var car2 = CarFactory.createCar(CarType.PASSENGER, "Audi11", "blue",
        new Engine("1000", 20.0, 100.0, TypeOfFuel.DIESEL, 3.0, 16), 4);
    var car3 = CarFactory.createCar(CarType.PASSENGER, "Audi22", "blue",
        new Engine("1000", 20.0, 100.0, TypeOfFuel.DIESEL, 3.0, 16), 4);
    var car4 = CarFactory.createCar(CarType.PASSENGER, "Audi33", "blue",
        new Engine("1000", 20.0, 100.0, TypeOfFuel.DIESEL, 3.0, 16), 4);
    var car5 = CarFactory.createCar(CarType.PASSENGER, "Audi44", "blue",
        new Engine("1000", 20.0, 100.0, TypeOfFuel.DIESEL, 3.0, 16), 4);
    var car6 = CarFactory.createCar(CarType.PASSENGER, "Audi55", "blue",
        new Engine("1000", 20.0, 100.0, TypeOfFuel.DIESEL, 3.0, 16), 4);

    base.addCarToBase(car1);
    base.addCarToBase(car2);
    base.addCarToBase(car3);
    base.addCarToBase(car4);
    base.addCarToBase(car5);
    res.append(base).append("\n\n");

    base.removeCarFromBase(car4);
    base.removeCarFromBase(car1);
    res.append(base).append("\n\n");

    base.sendCarToRepair(car2);
    base.sendCarToTransit(car3);
    res.append(base).append("\n\n");


    res.append(base.getCarsParked()).append("\n").append(base.getCarsInRepair()).append("\n").append(base.getCarsInTransit());
    return res.toString();

  }


}
