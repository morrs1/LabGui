package Programming.FourthSemLab.FourthLab;

import Programming.FourthSemLab.FourthLab.car.Car;

public class Main {

    public static void main(String[] args) {
        firstTask(" ");
    }

    public static String firstTask(String ignoredUnused) {
        Car car = new Car("Audi", Car.CarType.PASSENGER, "blue", 200, 4);
        System.out.println(car);
        return "";
    }
}
