class Cab extends Car {
  public Cab(String license, int eta) {
    super(license, eta);
  }

  @Override
  public String toString() {
    return "Cab " + super.toString();
  }

  @Override
  public boolean provides(Service service) {
    if ((service instanceof JustRide) || (service instanceof TakeACab)) {
      return true;
    }

    return false;
  }
}
