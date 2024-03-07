package Programming.FourthSemLab.FourthLab.cars;

public final class Bus extends Car{

    public Bus(String brand, String color, Engine engine, Integer amountOfWheels) {
        super(brand, color, engine, amountOfWheels);
    }

    public Bus(String regPlate, String brand, String color, Engine engine, Integer amountOfWheels) {
        super(regPlate, brand, color, engine, amountOfWheels);
    }

    @Override
    public String toString() {
        return "Bus{" +
            "regPlate='" + regPlate + '\'' +
            ", brand='" + brand + '\'' +
            ", color='" + color + '\'' +
            ", engine=" + engine +
            ", amountOfWheels=" + amountOfWheels +
            '}';
    }
}
