package Programming.FourthSemLab.FourthLab.cars;

public class SpecialCar extends Car{

    public SpecialCar(String regPlate, String brand, String color, Engine engine,
        Integer amountOfWheels) {
        super(regPlate, brand, color, engine, amountOfWheels);
    }

    public SpecialCar(String brand, String color, Engine engine, Integer amountOfWheels) {
        super(brand, color, engine, amountOfWheels);
    }

    @Override
    protected boolean checkRegPlate(String regPlate) {
        var regex = "\\d{4}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS";
        return regPlate.matches(regex);
    }

    @Override
    public String toString() {
        return "SpecialCar{" +
            "regPlate='" + regPlate + '\'' +
            ", brand='" + brand + '\'' +
            ", color='" + color + '\'' +
            ", engine=" + engine +
            ", amountOfWheels=" + amountOfWheels +
            '}';
    }
}
