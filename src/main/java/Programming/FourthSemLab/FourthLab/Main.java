package Programming.FourthSemLab.FourthLab;

import Programming.FourthSemLab.FourthLab.car.Car;

public class Main {

    public static void main(String[] args) {
//        firstTask(" ");
    }

    public static String firstTask(String ignoredUnused) {
        var res = new StringBuilder();
        Car car = new Car("Audi", Car.CarType.PASSENGER, "blue", 200, 4);
        res.append(car.getBrand()).append("\n").append(car.getType()).append("\n").append(car.getColor())
                .append("\n").append(car.getEnginePower()).append("\n").append(car.getAmountOfWheels()).append("\n");
        res.append(car.getRegPlate()).append("\n");
        car.setRegPlate("А123ВС77RUS");
        res.append(car.getRegPlate()).append("\n");
        car.setColor("Blue");
        res.append(car.getColor());
        car.setRegPlate("123ВС77RUS");
        return res.toString();
    }
    public static String secondTask(String ignoredUnused){

        return " fdfd";
    }
    public static String thirdTask(String ignoredUnused){

        return "";
    }
}
