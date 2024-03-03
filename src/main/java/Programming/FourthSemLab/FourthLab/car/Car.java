package Programming.FourthSemLab.FourthLab.car;

public class Car {
    private String regPlate;
    private String brand;
    private CarType type;
    private String color;
    private Integer enginePower;
    private Integer amountOfWheels;
    public enum CarType {PASSENGER, TRUCK, BUS}
    public Car(String brand, CarType type, String color, Integer enginePower, Integer amountOfWheels) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.enginePower = enginePower;
        this.amountOfWheels = amountOfWheels;
    }

    public Car(String regPlate, String brand, CarType type, String color, Integer enginePower, Integer amountOfWheels) {
        this.regPlate = regPlate;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.enginePower = enginePower;
        this.amountOfWheels = amountOfWheels;
    }

    public void setRegPlate(String regPlate) {
        this.regPlate = regPlate;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regPlate='" + regPlate + '\'' +
                ", brand='" + brand + '\'' +
                ", type=" + type +
                ", color='" + color + '\'' +
                ", enginePower=" + enginePower +
                ", amountOfWheels=" + amountOfWheels +
                '}';
    }
}
