package Programming.FourthSemLab.FourthLab.car;


public class Car {
    private String regPlate;
    private final String brand;
    private final CarType type;
    private String color;
    private Integer enginePower;
    private final Integer amountOfWheels;

    public enum CarType {PASSENGER, TRUCK, BUS}

    public Car(String brand, CarType type, String color, Integer enginePower, Integer amountOfWheels) {
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.enginePower = enginePower;
        this.amountOfWheels = amountOfWheels;
    }

    public Car(String regPlate, String brand, CarType type, String color, Integer enginePower, Integer amountOfWheels) {
        if (checkRegPlate(regPlate)) this.regPlate = regPlate;
        else
            System.out.println("Неправильно введен номер");
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.enginePower = enginePower;
        this.amountOfWheels = amountOfWheels;
    }

    public void setRegPlate(String regPlate) {
        if (checkRegPlate(regPlate)) this.regPlate = regPlate;
        else
            System.out.println("Неправильно введен номер");
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

    private boolean checkRegPlate(String regPlate) {
        var regex = "[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS";
        return regPlate.matches(regex);
    }

    public String getRegPlate() {
        return regPlate;
    }

    public String getBrand() {
        return brand;
    }

    public CarType getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public Integer getAmountOfWheels() {
        return amountOfWheels;
    }
}
