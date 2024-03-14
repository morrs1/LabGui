package Programming.FourthSemLab.FourthLab.cars;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CarBase {

  private final ArrayList<Car> carsParked = new ArrayList<>();
  private final ArrayList<Car> carsInTransit = new ArrayList<>();
  private final ArrayList<Car> carsInRepair = new ArrayList<>();
  private final Integer maxAmountOfCars;

  public CarBase(Integer maxAmountOfCars) {
    this.maxAmountOfCars = maxAmountOfCars;
  }



  public void addCarToBase(Car car){
    addCarToArray(car, carsParked);
  }
  public void removeCarFromBase(Car car) {
    removeCarFromArray(car, carsParked);
  }


  @Override
  public String toString() {
    return "CarBase{" +
        "carsParked=" + carsParked.stream().map(Car::getBrand).toList() +
        "\n carsInTransit=" + carsInTransit +
        "\n carsInRepair=" + carsInRepair +
        '}';
  }

  private void addCarToArray(Car car, ArrayList<Car> arr) {
    if (arr.size() < maxAmountOfCars) {
      arr.add(car);
    } else {
      throw new IllegalStateException("The array is full");
    }
  }

  private void removeCarFromArray(Car car, ArrayList<Car> arr) {
    if (!arr.contains(car)) {
      throw new IllegalArgumentException("The car is not in the array");
    }
    arr.remove(car);

  }
}
