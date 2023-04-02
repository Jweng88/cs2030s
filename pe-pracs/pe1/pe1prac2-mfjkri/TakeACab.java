class TakeACab implements Service {
  @Override
  public int computeFare(Request r) {
    return r.getDistFare(33) + 200;
  }

  @Override
  public String toString() {
    return "TakeACab";
  }
}
