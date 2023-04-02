package cs2030s.fp;

/**
 * This class encapsulates a value that is 
 * lazily evaluated.
 *
 * @author Fikri (Lab 12A)
 * @version CS2030S AY22/23 Semester 2
 */
public class Lazy<T> {
  /**
   * Producer that produces the value when evaluated.
   */
  private Producer<? extends T> producer;

  /**
   * Value of the Lazy instance.
   */
  private Maybe<T> value;

  /**
   * Private constructor to initialize value of instance
   * with given value encapsulated in a Maybe instance.
   *
   * @param value Value of the Lazy instance.
   */
  private Lazy(T value) {
    this.value = Maybe.<T>some(value);
  }

  /**
   * Private constructor to initialize producer of instance
   * with value unevaluated.
   *
   * @param producer Producer of the Lazy instance.
  
   */
  private Lazy(Producer<? extends T> producer) {
    this.value = Maybe.<T>none();
    this.producer = producer;
  }

  /**
   * Factory method of Lazy given a value.
   *
   * @param <U> Type of the Lazy instance.
   * @param value Value of the Lazy instance.
   * @return Lazy instance.
   */
  public static <U> Lazy<U> of(U value) {
    return new Lazy<U>(value);
  }

  /**
   * Factory method of Lazy given a producer.
   *
   * @param <U> Type of the Lazy instance.
   * @param producer Producer of the Lazy instance.
   * @return Lazy instance.
   */
  public static <U> Lazy<U> of(Producer<? extends U> producer) {
    return new Lazy<U>(producer);
  }

  /**
   * Public method to get value of Lazy instance. Computation
   * to evaluate value is only done at most once for the
   * same value.
   *
   * @return Value of the Lazy instance.
   */
  public T get() {
    T memoValue = this.value.orElseGet(this.producer);
    this.value = Maybe.<T>some(memoValue);
    return memoValue;
  }

  /**
   * Public method to return string representation of the 
   * value of Lazy instance.
   *
   * @return String representation of value.
   */
  @Override
  public String toString() {
    return this.value.map(String::valueOf).orElse("?");
  }

  /**
   * Public method to lazily map. The map is only evaluated
   * when get() is called and at most once.
   *
   * @param <U> Type of mapped Lazy instance.
   * @param transformer The transformer to be applied.
   * @return A new mapped Lazy instance of type U.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return Lazy.<U>of(() -> transformer.transform(this.get()));
  }

  /**
   * Public method to lazily flatmap. The flatmap is only
   * evaluated when get() is called and at most once.
   *
   * @param <U> Type of flatmapped Lazy instance.
   * @param transformer The transformer to be applied.
   * @return A new flatmapped Lazy instance of type U.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    return Lazy.<U>of(() -> transformer.transform(this.get()).get());
  }

  /**
   * Public method to lazily filter. The filter is only
   * evaluated when get() is called and at most once.
   *
   * @param conditioner The BooleanCondition used to lazily test value.
   * @return A new filtered Lazy of type Boolean  .
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> conditioner) {
    return Lazy.<Boolean>of(() -> conditioner.test(this.get()));
  }

  /**
   * Public equals method. Checks whether this instance is equal
   * to given object. This method will evaluate both Lazy instances
   * (if they haven't been) and returns true only if both are Lazy
   * and their respective evaluated values are equals.
   *
   * @param other The object to check equals with.
   * @return A boolean indicating if they're equal.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Lazy<?>) {
      Lazy<?> l = (Lazy<?>) other;

      return this.get().equals(l.get());
    }

    return false;
  }

  /**
   * Public combine method to lazily combine two Lazy object.
   *
   * @param <S> Type of second Lazy object.
   * @param <R> Type of lazily combined Lazy object.
   * @param other The second Lazy object to combine with.
   * @param combiner The combiner that will combine the two Lazy objects.
   * @return A new lazily combined Lazy object.
   */
  public <S, R> Lazy<R> combine(Lazy<? extends S> other, 
      Combiner<? super T, ? super S, ? extends R> combiner) { 
    return Lazy.<R>of(() -> combiner.combine(this.get(), other.get()));
  }
}
