package Programming.FourthSemLab.FourthLab.cars;

public class Passenger extends Car{
    public Passenger(String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(brand, type, color, engine, amountOfWheels);
    }
    public Passenger(String regPlate, String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(regPlate, brand, type, color, engine, amountOfWheels);
    }
}
