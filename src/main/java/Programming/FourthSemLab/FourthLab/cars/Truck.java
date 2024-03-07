package Programming.FourthSemLab.FourthLab.cars;

public class Truck extends Car{

    public Truck(String regPlate, String brand, String color, Engine engine,
        Integer amountOfWheels) {
        super(regPlate, brand, color, engine, amountOfWheels);
    }

    public Truck(String brand, String color, Engine engine, Integer amountOfWheels) {
        super(brand, color, engine, amountOfWheels);
    }

    @Override
    public String toString() {
        return "Truck{" +
            "regPlate='" + regPlate + '\'' +
            ", brand='" + brand + '\'' +
            ", color='" + color + '\'' +
            ", engine=" + engine +
            ", amountOfWheels=" + amountOfWheels +
            '}';
    }
}
