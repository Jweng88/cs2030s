import java.util.Scanner;

/**
 * The main class for CS2030S Lab 3.
 *
 * @author Wei Tsang
 * @version CS2030S AY22/23 Semester 2
 */
class Lab3 {
  public static void main(String[] args) {

    // Create a scanner to read from standard input.
    Scanner sc = new Scanner(System.in);

    // Create a simulation.  The BankSimulation 
    // constructor will read the simulation parameters 
    // and initial events using the scanner.
    Simulation simulation = new BankSimulation(sc);

    // Create a new simulator and run the simulation
    new Simulator(simulation).run();

    // Clean up the scanner.
    sc.close();
  }
}
