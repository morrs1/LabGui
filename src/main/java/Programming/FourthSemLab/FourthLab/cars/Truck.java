package Programming.FourthSemLab.FourthLab.cars;

public class Truck extends Car{
    public Truck(String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(brand, type, color, engine, amountOfWheels);
    }

    public Truck(String regPlate, String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(regPlate, brand, type, color, engine, amountOfWheels);
    }
}
