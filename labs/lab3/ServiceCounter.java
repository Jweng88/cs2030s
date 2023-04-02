/**
 * This class provides functionality for a single ServiceCounter.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class ServiceCounter implements Comparable<ServiceCounter> { 
  // CounterID 
  private final int id;
  // Internal ID used to increment CounterID
  private static int _id_counter = 0;

  private boolean available = true;
  private final Queue<Customer> counterQueue;
  
  public ServiceCounter(int counterQueueLen) {
    this.id = ServiceCounter._id_counter++;
    this.counterQueue = new Queue<Customer>(counterQueueLen);
  }

  @Override
  public String toString() {
    return String.format("S%d %s", this.id, this.counterQueue);
  }

  @Override
  public int compareTo(ServiceCounter other) {
    int thisLen = this.counterQueue.length();
    int otherLen = other.counterQueue.length();

    if (thisLen == otherLen) {
      return this.id - other.id;
    } else {
      return thisLen - otherLen;
    }
  }

  public boolean isAvailable() {
    return this.available;
  }

  public void makeAvailable() {
    this.available = true;
  }

  public void makeUnavailable() {
    this.available = false;
  }

  public Customer serveNextCustomer() {
    return this.counterQueue.deq();
  }

  public boolean isCounterFull() {
    return this.counterQueue.isFull();
  }

  public boolean joinCounterQueue(Customer customer) {
    return this.counterQueue.enq(customer);
  }
} 
