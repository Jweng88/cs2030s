class ServiceEndEvent extends Event {
  private Customer customer;
  private Bank bank;
  private ServiceCounter serviceCounter;
  private String serviceType;

  public ServiceEndEvent(double time, Customer customer, 
      Bank bank, ServiceCounter serviceCounter, String serviceType) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.serviceCounter = serviceCounter;
    this.serviceType = serviceType;
  }

  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s %s done (by %s)", 
      this.customer, this.serviceType, this.serviceCounter);
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // Customer can leave the counter (it gets marked as available)
    this.customer.leaveCounter(this.bank, this.serviceCounter);

    // Departure event for current customer
    BankDepartureEvent departure = new BankDepartureEvent(this.getTime(), this.customer);

    // Serve next customer if any
    Customer nextCustomer = this.bank.serveQueuingCustomer();
    if (nextCustomer == null) {
      // Entrance queue is already empty, schedule departure only
      return new Event[] { departure };
    } else {
      // Schedule departure and serve next customer in queue
      return new Event[] { 
        departure,
        new ServiceBeginEvent(this.getTime(), 
          nextCustomer, this.bank, this.serviceCounter)
      };
    }
  }
}