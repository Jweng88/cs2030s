/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Fikri (Lab 12A)
 */
class BoxIt<T> implements Transformer<T, Box<T>> {
  @Override
  public Box<T> transform(T content) {
    return Box.<T>ofNullable(content);
  }
}
