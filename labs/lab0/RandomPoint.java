import java.util.Random;

class RandomPoint extends Point {

  private static Random rnd = new Random(1);

  public RandomPoint (double minX, double maxX, double minY, double maxY){
    super(minX + (maxX - minX) * rnd.nextDouble(),  minY + (maxY - minY) * rnd.nextDouble());
  }

  public static void setSeed(long seed) {
    rnd.setSeed(seed);  
  }
}
