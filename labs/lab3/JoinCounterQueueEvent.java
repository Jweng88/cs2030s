class JoinCounterQueueEvent extends Event {
  private final Customer customer;
  private final Bank bank;
  private final ServiceCounter serviceCounter;

  public JoinCounterQueueEvent(double time, Customer customer, 
      Bank bank, ServiceCounter serviceCounter) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.serviceCounter = serviceCounter;
  }
  
  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined counter queue (at %s)", 
      this.customer, this.serviceCounter);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    this.customer.joinCounterQueue(this.bank, this.serviceCounter);
    return new Event[] {};
  }
}
