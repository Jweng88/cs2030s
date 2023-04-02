/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
public class EmptyList<T> implements SourceList<T> {
  
  @Override
  public T getFirst() {
    return null;
  }

  @Override
  public SourceList<T> getSecond() {
    return null;
  }

  @Override
  public String toString() {
    return "EmptyList";
  }

  @Override
  public int length() {
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (other instanceof EmptyList<?>) {
      return true;
    }

    return false;
  }

  @Override
  public EmptyList<T> filter(BooleanCondition<? super T> f) {
    return new EmptyList<T>();
  }

  @Override
  public <U> EmptyList<U> map(Transformer<? super T, ? extends U> t) {
    return new EmptyList<>();
  }
}
