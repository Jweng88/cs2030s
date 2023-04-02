class BankBeginEvent extends Event {
  private Customer customer;
  private Bank bank;
  private BankCounter bankCounter;

  public BankBeginEvent(double time, Customer customer, Bank bank, BankCounter bankCounter) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.bankCounter = bankCounter;
  }
  
  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s service begin (by %s)", this.customer, this.bankCounter);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    this.bankCounter.makeUnavailable();
    // a service-end event at the current time + service time.
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] { 
      new BankEndEvent(endTime, this.customer, this.bank, this.bankCounter)
    };
  }
}
