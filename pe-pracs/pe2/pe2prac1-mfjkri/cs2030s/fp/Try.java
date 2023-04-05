package cs2030s.fp;

/**
 * CS2030S PE2 Question 1.
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
public abstract class Try<T> {

  private static final class Failure extends Try<Object> {
    private final Throwable e;

    private Failure(Throwable e) {
      this.e = e;
    }

    @Override
    public Object get() throws Throwable {
      throw this.e;
    }

    @Override
    public <V> Try<V> map(Transformer<? super Object, ? extends V> trans) {
      return Try.failure(this.e); 
    }
    
    @Override
    public <V> Try<V> flatMap(Transformer<? super Object, ? extends Try<? extends V>> trans) {
      return Try.failure(this.e);
    }

    @Override
    public Try<Object> onFailure(Consumer<? super Throwable> consumer) {
      try {
        consumer.consume(this.e);
        return Try.failure(this.e);
      } catch (Throwable newE) {
        return Try.failure(newE);
      }
    }

    @Override
    public Try<Object> recover(Transformer<? super Throwable, ? extends Object> trans) {
      try {
        return Try.success(trans.transform(this.e));
      } catch (Throwable newE) {
        return Try.failure(newE);
      }
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (obj instanceof Failure) {
        Failure f = (Failure) obj;

        if (this.e == f.e) {
          return true;
        }

        if (this.e == null || f.e == null) {
          return false;
        }

        return this.e.toString() == f.e.toString();
      }

      return false;
    }
  }

  private static final class Success<U> extends Try<U> {
    private U value;

    private Success(U value) {
      this.value = value;
    }

    @Override
    public U get() throws Throwable {
      return this.value;
    }
    
    @Override
    public <V> Try<V> map(Transformer<? super U, ? extends V> trans) {
      return Try.of(() -> trans.transform(this.value));
    }

    @Override
    public <V> Try<V> flatMap(Transformer<? super U, ? extends Try<? extends V>> trans) {
      // It is safe to typecast here because blahblahblah
      @SuppressWarnings("unchecked")
      Try<V> transGuy = (Try<V>) trans.transform(this.value); 
      return transGuy;
    }

    @Override
    public Try<U> onFailure(Consumer<? super Throwable> consumer) {
      return this;
    }

    @Override
    public Try<U> recover(Transformer<? super Throwable, ? extends U> trans) {
      return this;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (obj instanceof Success<?>) {
        Success<?> s = (Success<?>) obj;
        if (this.value == s.value) {
          return true;
        }

        if (this.value == null || s.value == null) {
          return false;
        }

        return this.value.equals(s.value);
      }

      return false;
    }
  }

  public static <U> Try<U> success(U value) {
    return new Success<U>(value);
  }

  public static <U> Try<U> failure(Throwable e) {
    // It is safe to typecast here because blahblahblah
    @SuppressWarnings("unchecked")
    Try<U> failureObj = (Try<U>) new Failure(e);
    return failureObj;
  }

  public static <U> Try<U> of(Producer<? extends U> producer) {
    try {
      return Try.success(producer.produce());
    } catch (Throwable e) {
      return Try.failure(e);
    }
  }

  public abstract T get() throws Throwable;

  public abstract <U> Try<U> map(Transformer<? super T, ? extends U> trans);

  public abstract <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> trans);

  public abstract Try<T> onFailure(Consumer<? super Throwable> consumer);

  public abstract Try<T> recover(Transformer<? super Throwable, ? extends T> trans);
}
