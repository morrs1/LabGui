package Programming.FourthSemLab.FourthLab.cars;

public class SpecialCar extends Car{
    public SpecialCar(String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(brand, type, color, engine, amountOfWheels);
    }

    public SpecialCar(String regPlate, String brand, CarType type, String color, Engine engine, Integer amountOfWheels) {
        super(regPlate, brand, type, color, engine, amountOfWheels);
    }

    @Override
    protected boolean checkRegPlate(String regPlate) {

        return true;
    }
}
