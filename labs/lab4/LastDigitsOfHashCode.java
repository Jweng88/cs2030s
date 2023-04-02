import java.lang.Math;

/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Fikri (Lab 12A)
 */
class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private final int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  @Override
  public Integer transform(Object from) {
    return Math.abs(from.hashCode()) % ((int) Math.pow(10, k)); 
  }
}

