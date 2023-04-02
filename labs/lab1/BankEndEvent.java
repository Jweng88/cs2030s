class BankEndEvent extends Event {
  private Customer customer;
  private Bank bank;
  private BankCounter bankCounter;

  public BankEndEvent(double time, Customer customer, Bank bank, BankCounter bankCounter) {
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
    return super.toString() + String.format(": %s service done (by %s)", this.customer, this.bankCounter);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    this.customer.leaveCounter(this.bank, this.bankCounter);
    return new Event[] { 
      new BankDepartureEvent(this.getTime(), this.customer)
    };
  }
}
