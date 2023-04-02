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
  
  // Customer arrival and service time
  private double arrivalTime;
  private double serviceTime;

  public Customer(double arrivalTime, double serviceTime) {
    this.id = Customer._id_counter++;

    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  @Override
  public String toString() {
    return String.format("Customer %d", this.id);
  }

  public BankCounter goToCounter(Bank bank) {
    return bank.getAvailableCounter();
  }

  public void leaveCounter(Bank bank, BankCounter bankCounter) {
    bank.returnCounter(bankCounter);
  }

  public double getArrivalTime() {
    return this.arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }
}
