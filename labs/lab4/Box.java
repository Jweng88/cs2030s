/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Fikri (Lab 12A)
 */
class Box<T> {
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private final T content;

  private Box(T content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } 

    if (obj instanceof Box<?>) {
      Box<?> b = (Box<?>) obj;
      if (this.content == b.content) {
        return true;
      }

      if (this.content == null || b.content == null) {
        return false;
      }

      return this.content.equals(b.content);
    }

    return false;
  }

  @Override
  public String toString() {
    if (this.isPresent()) {
      return String.format("[%s]", this.content);
    } else {
      return "[]";
    }
  }

  public static <S> Box<S> of(S content) {
    if (content == null) {
      return null;
    }
    return new Box<S>(content);  
  }

  public static <S> Box<S> empty() {
    @SuppressWarnings("unchecked")
    Box<S> s = (Box<S>) Box.EMPTY_BOX;
    return s;
  }

  public boolean isPresent() {
    return this.content != null;
  }

  public static <S> Box<S> ofNullable(S content) {
    if (content != null) {
      return Box.<S>of(content);
    } else {
      return Box.<S>empty();
    }
  }

  public Box<T> filter(BooleanCondition<? super T> tester) {
    if (!this.isPresent() || !tester.test(this.content)) {
      return Box.<T>empty();
    } else {
      return this;
    }
  }

  public <U> Box<U> map(Transformer<? super T, ? extends U> transform) {
    if (!this.isPresent()) {
      return Box.<U>empty();
    }
    return Box.<U>ofNullable(transform.transform(this.content));
  }
}
