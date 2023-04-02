/**
 * This class provides functionality for a single Customer.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class Customer {
  // CustomerID 
  private int id; 
  // Internal ID used to increment each Customer ID
  private static int _id_counter = 0;
  
  private double arrivalTime;
  private double serviceTime;
  private int task;

  public Customer(double arrivalTime, double serviceTime, int task) {
    this.id = Customer._id_counter++;

    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.task = task;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.id);
  }

  public String getTask() {
    if (this.task == 0) {
      return "Deposit";
    } else {
      return "Withdrawal";
    }
  }

  public ServiceCounter goToCounter(Bank bank) {
    return bank.getAvailableCounter();
  }

  public void useCounter(Bank bank, ServiceCounter serviceCounter) {
    bank.useCounter(serviceCounter);
  }

  public void leaveCounter(Bank bank, ServiceCounter serviceCounter) {
    bank.returnCounter(serviceCounter);
  }

  public boolean joinQueue(Bank bank) {
    return bank.joinQueue(this);
  }

  public double getArrivalTime() {
    return this.arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }
}
