package Programming.FourthSemLab.FourthLab.cars;

public class Passenger extends Car{

    public Passenger(String regPlate, String brand, String color, Engine engine,
        Integer amountOfWheels) {
        super(regPlate, brand, color, engine, amountOfWheels);
    }

    public Passenger(String brand, String color, Engine engine, Integer amountOfWheels) {
        super(brand, color, engine, amountOfWheels);
    }

    @Override
    public String toString() {
        return "Passenger{" +
            "regPlate='" + regPlate + '\'' +
            ", brand='" + brand + '\'' +
            ", color='" + color + '\'' +
            ", engine=" + engine +
            ", amountOfWheels=" + amountOfWheels +
            '}';
    }
}
