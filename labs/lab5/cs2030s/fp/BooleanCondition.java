package cs2030s.fp;

/**
 * A conditional statement that returns either true of false.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Fikri (Lab 12A)
 */
public interface BooleanCondition<T> {
  public boolean test(T x);
}
