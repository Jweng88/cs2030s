class JoinEntranceQueueEvent extends Event {
  private final Customer customer;
  private final Bank bank;

  public JoinEntranceQueueEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }
  
  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined bank queue %s", 
      this.customer, this.bank);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    this.customer.joinEntranceQueue(this.bank);
    return new Event[] {};
  }
}
