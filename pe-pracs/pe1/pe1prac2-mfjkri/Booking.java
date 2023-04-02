class Booking implements Comparable<Booking> {
  private final Car car;
  private final Service service;
  private final Request request;
  private final int fare;

  public Booking(Car car, Service service, Request request) throws IllegalArgumentException {
    if (!car.provides(service)) {
      throw new IllegalArgumentException(
          String.format("%s does not provide the %s service.", car, service));
    }

    this.car = car;
    this.service = service;
    this.request = request;
    this.fare = service.computeFare(request);
  }
  
  @Override
  public int compareTo(Booking other) {
    if (this.fare == other.fare) {
      return this.car.compareTo(other.car);
    }

    return this.fare - other.fare;
  }
}
