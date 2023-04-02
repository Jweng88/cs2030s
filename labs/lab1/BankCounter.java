/**
 * This class provides functionality for a single BankCounter.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class BankCounter { 
  // CounterID 
  private int id;
  // Internal ID used to increment CounterID
  private static int _id_counter = 0;

  private boolean available = true;
  
  public BankCounter() {
    this.id = BankCounter._id_counter++;
  }

  @Override
  public String toString() {
    return String.format("Counter %d", this.id);
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
} 
