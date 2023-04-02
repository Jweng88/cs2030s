class ServiceBeginEvent extends Event {
  private final Customer customer;
  private final Bank bank;
  private final ServiceCounter serviceCounter;
  private final ServiceTask serviceTask;

  public ServiceBeginEvent(double time, Customer customer, 
      Bank bank, ServiceCounter serviceCounter) {
    super(time);
    this.customer = customer;
    this.bank = bank;
    this.serviceCounter = serviceCounter;
    this.serviceTask = customer.getTask();
  }
  
  /**
   * Returns the string representation of the event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s %s begin (by %s)", 
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
    // Customer should start their service at the counter
    this.customer.useCounter(this.bank, this.serviceCounter);

    // a service-end event at the current time + service time.
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] { 
      new ServiceEndEvent(endTime, this.customer, this.bank, 
        this.serviceCounter, this.serviceTask)
    };
  }
}