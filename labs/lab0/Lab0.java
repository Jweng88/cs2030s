import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2022/23
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author mfjkri 
 */

class Lab0 {

  public static double estimatePi(int numOfPoints, int seed) {
    Circle c = new Circle(new Point(0.5, 0.5), 0.5);
    double withinCircle = 0.0;

    RandomPoint.setSeed(seed);

    for (int i = 0; i < numOfPoints; i++) {
      Point p = new RandomPoint(0, 1, 0, 1);
      
      if (c.contains(p)) {
        withinCircle += 1.0;
      }
    }

    double piEst = 4 * withinCircle / numOfPoints;
    return piEst;
    // return 4 * withinCircle / numOfPoints;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
