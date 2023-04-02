/**
 * This class provides encapsulation for Bank.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class Bank {
  private final Queue<Customer> entranceQueue;
  private final Array<ServiceCounter> counters;

  public Bank(int numOfCounters, int counterQueueLen, int entranceQueueLen) {
    this.entranceQueue = new Queue<Customer>(entranceQueueLen);
    this.counters = new Array<ServiceCounter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      this.counters.set(i, new ServiceCounter(counterQueueLen));
    }
  }

  @Override
  public String toString() {
    return this.entranceQueue.toString();
  }

  public ServiceCounter getAvailableCounter() {
    for (int i = 0; i < this.counters.length(); i++) {
      ServiceCounter serviceCounter = this.counters.get(i);
      if (serviceCounter.isAvailable()) {
        return serviceCounter;
      }
    }

    return null;
  }

  public ServiceCounter getShortestQueueCounter() {
    ServiceCounter counter = this.counters.min();
    if (counter == null || counter.isCounterFull()) {
      return null;
    }

    return counter;
  }

  public void useCounter(ServiceCounter counter) {
    counter.makeUnavailable();
  }

  public void returnCounter(ServiceCounter counter) {
    counter.makeAvailable();
  }

  public Customer admitNextCustomer() {
    return this.entranceQueue.deq();
  }

  public Customer serveNextCustomer(ServiceCounter counter) {
    return counter.serveNextCustomer();
  }

  public boolean canJoinEntranceQueue() {
    return !this.entranceQueue.isFull();
  }
  
  public boolean joinEntranceQueue(Customer customer) {
    return this.entranceQueue.enq(customer);
  }

  public boolean joinCounterQueue(ServiceCounter counter, Customer customer) {
    return counter.joinCounterQueue(customer);
  }
}