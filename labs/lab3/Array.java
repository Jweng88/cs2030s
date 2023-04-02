/**
 * The Array<T> for CS2030S 
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> {
  private T[] array;
  private int size;

  Array(int size) {
    // The only way we can put an object into array is through
    // the method set() and we only put object of type T inside.
    // So it is safe to cast Comparable[] to T[].
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;

    this.size = size;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    T smallest = null;
    for (T elem : this.array) {
      // Prevent us from accidentally comparing with an null reference
      // in case the array isn't fully populated
      if (elem == null) {
        continue;
      }

      if (smallest == null || smallest.compareTo(elem) > 0) {
        smallest = elem;
      }
    }

    return smallest;
  }

  public int length() {
    return this.size;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
