class ShareARide implements Service {
  @Override
  public int computeFare(Request r) {
    return (r.getDistFare(50) / r.getPassengers()) + r.getSurcharge(500, 600, 900);
  }

  @Override
  public String toString() {
    return "ShareARide";
  }
}

