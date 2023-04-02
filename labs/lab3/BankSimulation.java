import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class BankSimulation extends Simulation {
  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private final Event[] initEvents;
  private final Bank bank;

  /** 
   * Constructor for a bank simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public BankSimulation(Scanner sc) {
    int numOfCustomers = sc.nextInt();
    int numOfCounters = sc.nextInt();
    int counterQueueLen = sc.nextInt();
    int entranceQueueLen = sc.nextInt();

    this.initEvents = new Event[numOfCustomers];
    this.bank = new Bank(numOfCounters, counterQueueLen, entranceQueueLen);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int task = sc.nextInt();

      Customer customer = new Customer(arrivalTime, serviceTime, task);

      this.initEvents[id] = new BankArrivalEvent(customer, this.bank);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}

