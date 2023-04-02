/**
 * This class provides functionality for a single Customer.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class Customer {
  // CustomerID 
  private final int id; 
  // Internal ID used to increment each Customer ID
  private static int _id_counter = 0;
  
  private final double arrivalTime;
  private final double serviceTime;
  private final ServiceTask task;

  public Customer(double arrivalTime, double serviceTime, int taskId) {
    this.id = Customer._id_counter++;

    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.task = new ServiceTask(taskId);
  }

  @Override
  public String toString() {
    return String.format("C%d", this.id);
  }

  public ServiceTask getTask() {
    return this.task;
  }

  public double getArrivalTime() {
    return this.arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }


  /**
  * Customer actions
  * These methods are meant to simulate possible customer actions
  * All these methods will use the Bank object as a middleman when
  * interacting with a ServiceCounter
  */ 

  public ServiceCounter goToAvailableCounter(Bank bank) {
    return bank.getAvailableCounter();
  }

  public ServiceCounter goToCounterWithShortestQueue(Bank bank) {
    return bank.getShortestQueueCounter();
  }

  public void useCounter(Bank bank, ServiceCounter serviceCounter) {
    bank.useCounter(serviceCounter);
  }

  public void leaveCounter(Bank bank, ServiceCounter serviceCounter) {
    bank.returnCounter(serviceCounter);
  }

  public boolean joinEntranceQueue(Bank bank) {
    return bank.joinEntranceQueue(this);
  }

  public boolean joinCounterQueue(Bank bank, ServiceCounter serviceCounter) {
    return bank.joinCounterQueue(serviceCounter, this);
  }
}
