package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * This class encapsulates a value that is maybe null.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY22/23 Semester 2
 */
public abstract class Maybe<T> {
  private static final class None extends Maybe<Object> {
    private static None none = new None();

    private None() {
    }

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof None;
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> conditioner) {
      return Maybe.<Object>none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> trans) {
      return Maybe.<U>none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> trans) {
      return Maybe.<U>none();
    }

    @Override
    public Object orElse(Object value) {
      return value;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> prod) {
      return prod.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> con) {
    }
  }

  private static final class Some<T> extends Maybe<T> {
    private T t;

    private Some(T t) {
      this.t = t;
    }

    @Override
    public String toString() {
      return String.format("[%s]", this.t);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null) {
        return false;
      }

      if (o instanceof Some<?>) {
        Some<?> s = (Some<?>) o;

        if (this.t == s.t) {
          return true;
        }

        if (this.t == null || s.t == null) {
          return false;
        }

        return this.t.equals(s.t);
      }
      return false;
    }

    @Override
    protected T get() {
      return this.t;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> conditioner) {
      if (this.get() != null && !conditioner.test(this.get())) {
        return Maybe.<T>none();
      }

      return this;
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> trans) {
      return Maybe.<U>some(trans.transform(this.get()));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans) {
      @SuppressWarnings("unchecked")
      Maybe<U> transGuy = (Maybe<U>) trans.transform(this.get());
      return transGuy;
    }

    @Override
    public <U extends T> T orElse(U value) {
      return this.get();
    }

    @Override
    public T orElseGet(Producer<? extends T> prod) {
      return this.t;
    }

    @Override
    public void ifPresent(Consumer<? super T> con) {
      con.consume(this.t);
    }
  }

  public static <S> Maybe<S> none() {
    // It is safe to tpye cast here as
    // None is a class with nothingness
    // and we cannot modify it
    // S <: Object
    @SuppressWarnings("unchecked")
    Maybe<S> noneObj = (Maybe<S>) None.none;
    return noneObj;
  }

  public static <S> Maybe<S> some(S t) {
    return new Some<S>(t);
  }

  public static <S> Maybe<S> of(S input) {
    if (input == null) {
      return Maybe.<S>none();
    } else {
      return Maybe.<S>some(input);
    }
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> conditioner);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> trans); 

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans);

  public abstract <U extends T> T orElse(U value);

  public abstract T orElseGet(Producer<? extends T> prod);

  public abstract void ifPresent(Consumer<? super T> con);
}
