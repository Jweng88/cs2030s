/**
 * This class provides encapsulation for Bank.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class Bank {
  private BankCounter[] counters;

  public Bank(int numOfCounters) {
    this.counters = new BankCounter[numOfCounters];

    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new BankCounter();
    }  
  }

  public BankCounter getAvailableCounter() {
    for (BankCounter bankCounter : this.counters) {
      if (bankCounter.isAvailable()) {
        return bankCounter;
      }
    }

    return null;
  }

  public static void returnCounter(BankCounter counter) {
    counter.makeAvailable();
  }
}



