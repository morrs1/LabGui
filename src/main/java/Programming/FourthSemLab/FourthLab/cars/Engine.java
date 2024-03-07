package Programming.FourthSemLab.FourthLab.cars;

public record Engine(String serialNumber, Double capacity, Double workingVolume,
                     Programming.FourthSemLab.FourthLab.cars.Engine.TypeOfFuel typeOfFuel,
                     Double fuelUsage, Integer amountOfCylinders) {

  public enum TypeOfFuel {
    DIESEL, PETROL
  }

  @Override
  public String toString() {
    return "Engine{" +
        "serialNumber='" + serialNumber + '\'' +
        ", capacity=" + capacity +
        ", workingVolume=" + workingVolume +
        ", typeOfFuel=" + typeOfFuel +
        ", fuelUsage=" + fuelUsage +
        ", amountOfCylinders=" + amountOfCylinders +
        '}';
  }

}
