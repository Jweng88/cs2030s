abstract class Car implements Comparable<Car> {
  private final String license;
  private final int eta;

  public Car(String license, int eta) {
    this.license = license;
    this.eta = eta;
  }

  @Override
  public String toString() {
    String minStr = String.format("min");
    if (this.eta > 1) {
      minStr = "mins";
    } 

    return String.format("%s (%d %s away)", this.license, this.eta, minStr);
  }

  @Override
  public int compareTo(Car other) {
    return this.eta - other.eta;
  }

  public abstract boolean provides(Service service);
}
