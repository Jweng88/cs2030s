class PrivateCar extends Car {
  public PrivateCar(String license, int eta) {
    super(license, eta);
  }

  @Override
  public String toString() {
    return "PrivateCar " + super.toString();
  }

  @Override
  public boolean provides(Service service) {
    if ((service instanceof JustRide) || (service instanceof ShareARide)) {
      return true;
    }

    return false;
  }
}
