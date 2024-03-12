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

  public void addCarToBase(Car car) {
    if (carsParked.size() < maxAmountOfCars) {
      carsParked.add(car);
    } else {
      throw new IllegalStateException("The array is full");
    }
  }

  public void removeCarFromBase(Car car) {
    removeCarFromArray(car, carsParked);
  }

  public void removeCarFromArray(Car car, ArrayList<Car> arr) {
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i) == car) {
        arr.remove(i);
        return;
      }
    }
    throw new IllegalArgumentException("The car is not in the array");
  }

  @Override
  public String toString() {
    return "CarBase{" +
        "carsParked=" + carsParked.stream().map(Car::getBrand).toList() +
        "\n carsInTransit=" + carsInTransit +
        "\n carsInRepair=" + carsInRepair +
        '}';
  }
}
