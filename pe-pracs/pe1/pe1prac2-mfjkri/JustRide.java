class JustRide implements Service {
  @Override
  public int computeFare(Request r) {
    return r.getDistFare(22) + r.getSurcharge(500, 600, 900);
  }

  @Override
  public String toString() {
    return "JustRide";
  }
}
