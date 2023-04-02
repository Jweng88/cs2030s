class ServiceBeginEvent extends Event {
  private Customer customer;
  private Bank bank;
  private ServiceCounter serviceCounter;
  private String serviceType;

  public ServiceBeginEvent(double time, Customer customer, 
      Bank bank, ServiceCounter serviceCounter) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.serviceCounter = serviceCounter;
    this.serviceType = customer.getTask();
  }
  
  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s %s begin (by %s)", 
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
    // Customer should start their service at the counter
    this.customer.useCounter(this.bank, this.serviceCounter);

    // a service-end event at the current time + service time.
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] { 
      new ServiceEndEvent(endTime, this.customer, this.bank, this.serviceCounter, this.serviceType)
    };
  }
}