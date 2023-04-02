class Request {
  private int dist;
  private int passengers;
  private int time;

  public Request(int dist, int passengers, int time) {
    this.dist = dist;
    this.passengers = passengers;
    this.time = time;
  }

  public int getDistFare(int rate) {
    return this.dist * rate;
  }

  public int getSurcharge(int rate, int start, int end) {
    if (this.time >= start && this.time <= end) {
      return rate;
    }

    return 0;
  }

  public int getPassengers() {
    return this.passengers;
  }
}
