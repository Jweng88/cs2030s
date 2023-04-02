class BankArrivalEvent extends Event {
  private final Bank bank;
  private final Customer customer;

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
    ServiceCounter serviceCounter = this.customer.goToAvailableCounter(this.bank);
    if (serviceCounter != null) {
      // The customer should go the the first available counter and get served.
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, this.bank, serviceCounter)  
      };
    }

    // Failed to find available counter
    serviceCounter = this.customer.goToCounterWithShortestQueue(this.bank);
    if (serviceCounter != null) {
      // Customer should join the counter with the shortest queue
      return new Event[] {
        new JoinCounterQueueEvent(this.getTime(), this.customer, 
          this.bank, serviceCounter)
      };
    }

    // All counters are full
    if (this.bank.canJoinEntranceQueue()) {
      // If all counter are full, and bank entrance queue is not full,
      // customer should join entrance queue
      return new Event[] {
        new JoinEntranceQueueEvent(this.getTime(), this.customer, this.bank)
      };
    } else {
      // Everything is full, customer should depart
      return new Event[] {
        new BankDepartureEvent(this.getTime(), this.customer)
      };
    }
  }
}
