/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
public class Pair<T> implements SourceList<T> {
  private T first;
  private SourceList<T> second;

  public Pair(T first, SourceList<T> second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public T getFirst() {
    return this.first;
  }

  @Override
  public SourceList<T> getSecond() {
    return this.second;
  }

  @Override
  public String toString() {
    return this.first + ", " + this.second;
  }

  @Override
  public int length() {
    return 1 + this.second.length();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other instanceof Pair<?>) {
      Pair<?> p = (Pair<?>) other;

      if (this.first == null && p.second != null) {
        return false;
      }

      if (this.second == null && p.second != null) {
        return false;
      }

      if (this.first.equals(p.first)) {
        return this.second.equals(p.second);
      }
    }

    return false;
  }

  @Override
  public SourceList<T> filter(BooleanCondition<? super T> f) {
    if (f.test(this.first)) {
      return new Pair<>(this.first, this.second.filter(f));
    } else {
      return this.second.filter(f);
    }
  }

  @Override
  public <U> SourceList<U> map(Transformer<? super T, ? extends U> t) {
    return new Pair<>(t.transform(this.first), this.second.map(t));
  }
}

