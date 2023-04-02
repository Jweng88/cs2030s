class ServiceEndEvent extends Event {
  private final Customer customer;
  private final Bank bank;
  private final ServiceCounter serviceCounter;
  private final ServiceTask serviceTask;

  public ServiceEndEvent(double time, Customer customer, 
      Bank bank, ServiceCounter serviceCounter, ServiceTask serviceTask) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.serviceCounter = serviceCounter;
    this.serviceTask = serviceTask;
  }

  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s %s done (by %s)", 
      this.customer, this.serviceTask, this.serviceCounter);
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

    // Serve and admit next customers if any
    Customer nextCustomerToService = this.bank.serveNextCustomer(this.serviceCounter);
    Customer nextCustomerToAdmit = this.bank.admitNextCustomer();

    if (nextCustomerToService == null && nextCustomerToAdmit == null) {
      // No new customers to serve or admit
      return new Event[] { this.makeDeparture() };
    } else if (nextCustomerToService != null && nextCustomerToAdmit != null) {
      // There is a customer to serve and a customer to admit
      // Customer in counterQueue will be served
      // Customer in entranceQueue will be admitted (join counterQueue)
      return new Event[] { 
        this.makeDeparture(),
        this.makeNewService(nextCustomerToService),
        new JoinCounterQueueEvent(this.getTime(),
          nextCustomerToAdmit, this.bank, this.serviceCounter)
      };
    } else {
      // There is only one of the two to serve (exclusive),
      if (nextCustomerToService != null) {
        return new Event[] {
          this.makeDeparture(), 
          this.makeNewService(nextCustomerToService)
        };
      } else {
        return new Event[] {
          this.makeDeparture(), 
          this.makeNewService(nextCustomerToAdmit)
        };
      }
    }
  }

  public Event makeDeparture() {
    return new BankDepartureEvent(this.getTime(), this.customer);
  }

  public Event makeNewService(Customer customerToServe) {
    return new ServiceBeginEvent(this.getTime(), 
      customerToServe, this.bank, this.serviceCounter);
  }
}
