package Programming.FourthSemLab.FourthLab.cars;

public class Bus extends Car{
    public Bus(String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(brand, type, color, engine, amountOfWheels);
    }

    public Bus(String regPlate, String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(regPlate, brand, type, color, engine, amountOfWheels);
    }
}
