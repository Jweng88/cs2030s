/**
 * This class provides encapsulation for Bank.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class Bank {
  private ServiceCounter[] counters;
  private Queue entranceQueue;

  public Bank(int numOfCounters, int entranceQueueLen) {
    this.counters = new ServiceCounter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new ServiceCounter();
    }  

    this.entranceQueue = new Queue(entranceQueueLen);
  }

  public ServiceCounter getAvailableCounter() {
    for (ServiceCounter serviceCounter : this.counters) {
      if (serviceCounter.isAvailable()) {
        return serviceCounter;
      }
    }

    return null;
  }

  @Override
  public String toString() {
    return this.entranceQueue.toString();
  }

  public void useCounter(ServiceCounter counter) {
    counter.makeUnavailable();
  }

  public void returnCounter(ServiceCounter counter) {
    counter.makeAvailable();
  }

  public boolean canJoinQueue() {
    return !this.entranceQueue.isFull();
  }

  public boolean joinQueue(Customer customer) {
    return this.entranceQueue.enq(customer);
  }

  public Customer serveQueuingCustomer() {
    return (Customer) this.entranceQueue.deq();
  }
}