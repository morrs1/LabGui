package Programming.FourthSemLab.FourthLab.cars;

public class Engine {

  private String serialNumber;
  private Double capacity;
  private Double workingVolume;
  private TypeOfFuel typeOfFuel;
  private Double fuelUsage;
  private Integer amountOfCylinders;

  public enum TypeOfFuel {
    DIESEL, PETROL
  }

  public Engine(String serialNumber, Double capacity, Double workingVolume, TypeOfFuel typeOfFuel,
      Double fuelUsage, Integer amountOfCylinders) {
    this.serialNumber = serialNumber;
    this.capacity = capacity;
    this.workingVolume = workingVolume;
    this.typeOfFuel = typeOfFuel;
    this.fuelUsage = fuelUsage;
    this.amountOfCylinders = amountOfCylinders;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public Double getCapacity() {
    return capacity;
  }

  public Double getWorkingVolume() {
    return workingVolume;
  }

  public TypeOfFuel getTypeOfFuel() {
    return typeOfFuel;
  }

  public Double getFuelUsage() {
    return fuelUsage;
  }

  public Integer getAmountOfCylinders() {
    return amountOfCylinders;
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
