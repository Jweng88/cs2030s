class BankArrivalEvent extends Event {
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
    return super.toString() + String.format(": %s arrived %s", 
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
    ServiceCounter serviceCounter = this.customer.goToCounter(this.bank);

    if (serviceCounter != null) {
      // The customer should go the the first 
      // available counter and get served.
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, this.bank, serviceCounter)  
      };
    } else {
      // If no counter, customer should try and join queue
      if (this.bank.canJoinQueue()) {
        // Customer will join the entrance queue
        return new Event[] {
          new JoinQueueEvent(this.getTime(), this.customer, this.bank)
        };
      } else {
        // Entrance queue is full, customer should depart
        return new Event[] {
          new BankDepartureEvent(this.getTime(), this.customer)
        };
      }
    }
  }
}