package cs2030s.fp;

/**
 * Represent a function that consumes a value.
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @param <T> The type of the value consumed.
 */
@FunctionalInterface
public interface Consumer<T> {
  /**
   * The functional method to consume a value.
   *
   * @param t The value consumed.
   */
  void consume(T t);
}
