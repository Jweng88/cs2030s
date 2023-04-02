class BankDepartureEvent extends Event {
  private final Customer customer;

  public BankDepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }
  
  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s departed", this.customer);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // do nothing
    return new Event[] {};
  }
}