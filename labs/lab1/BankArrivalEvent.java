class BankArrivalEvent extends Event{
  private Bank bank;
  private Customer customer;

  public BankArrivalEvent(Customer customer, Bank bank) {
    super(customer.getArrivalTime());
    this.bank = bank;
    this.customer = customer;
  }

  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrives", this.customer);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    BankCounter bankCounter = this.customer.goToCounter(this.bank);

    if (bankCounter != null) {
      // The customer should go the the first 
      // available counter and get served.
      return new Event[] {
        new BankBeginEvent(this.getTime(), this.customer, this.bank, bankCounter)  
      };
    } else {
      // If no such counter can be found, the customer
      // should depart.
      return new Event[] {
        new BankDepartureEvent(this.getTime(), this.customer)
      };
    }
  }
}
