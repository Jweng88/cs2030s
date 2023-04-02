package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S PE2 Question 1
 * AY21/22 Semester 2.
 *
 * @author A0259234B
 */
public abstract class Either<L, R> {
  public abstract boolean isLeft();

  public abstract boolean isRight();

  public abstract L getLeft() throws NoSuchElementException;

  public abstract R getRight() throws NoSuchElementException;

  public abstract <A, B> Either<A, B> map(
      Transformer<? super L, ? extends A> leftTrans, 
      Transformer<? super R, ? extends B> rightTrans); 

  public abstract <A, B> Either<A, B> flatMap(
      Transformer<? super L, ? extends Either<? extends A, R>> leftTrans, 
      Transformer<? super R, ? extends Either<L, ? extends B>> rightTrans); 

  public abstract <A> A fold(
      Transformer<? super L, ? extends A> leftTrans, 
      Transformer<? super R, ? extends A> rightTrans); 

  public abstract <A> Either<? extends Object, ? extends Object> filterOrElse(
      BooleanCondition<? super R> conditioner,
      Transformer<? super R, ? extends A> trans);

  public static <A, B> Either<A, B> left(A value) {
    @SuppressWarnings("unchecked")
    Either<A, B> n = (Either<A, B>) new Left<A>(value);
    return n;
  }

  public static <A, B> Either<A, B> right(B value) {
    @SuppressWarnings("unchecked")
    Either<A, B> n = (Either<A, B>) new Right<B>(value);
    return n;
  }

  private static class Left<T> extends Either<T, Object> {
    private T value;

    private Left(T value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return true;
    }

    @Override
    public boolean isRight() {
      return false;
    }

    @Override
    public T getLeft() throws NoSuchElementException {
      return this.value;
    }

    @Override
    public Object getRight() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public <A, B> Either<A, B> map(
        Transformer<? super T, ? extends A> leftTrans, 
        Transformer<? super Object, ? extends B> rightTrans) { 
      return Either.left(leftTrans.transform(this.value));
    }

    @Override
    public <A, B> Either<A, B> flatMap(
        Transformer<? super T, ? extends Either<? extends A, Object>> leftTrans, 
        Transformer<? super Object, ? extends Either<T, ? extends B>> rightTrans) {
      @SuppressWarnings("unchecked")
      Either<A, B> transGuy = (Either<A, B>) leftTrans.transform(this.value);
      return transGuy;
    }

    @Override
    public <A> A fold(
        Transformer<? super T, ? extends A> leftTrans, 
        Transformer<? super Object, ? extends A> rightTrans) {
      return leftTrans.transform(this.value);
    }

    @Override
    public <A> Either<T, A> filterOrElse(
        BooleanCondition<? super Object> conditioner,
        Transformer<? super Object, ? extends A> trans) {
      return Either.left(this.value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      }

      if (other instanceof Left<?>) {
        Left<?> l = (Left<?>) other;

        if (this.value == l.value) {
          return true;
        }

        if (this.value == null || l.value == null) {
          return false;
        }

        return this.value.equals(l.value);
      }

      return false;
    }

    @Override
    public String toString() {
      return String.format("Left[%s]", this.value);
    }
  }

  private static class Right<T> extends Either<Object, T> {
    private T value;

    private Right(T value) {
      this.value = value;
    }

    @Override
    public boolean isLeft() {
      return false;
    }

    @Override
    public boolean isRight() {
      return true;
    }

    @Override
    public Object getLeft() throws NoSuchElementException {
      throw new NoSuchElementException();
    }

    @Override
    public T getRight() throws NoSuchElementException {
      return this.value;
    }

    @Override
    public <A, B> Either<A, B> map(
        Transformer<? super Object, ? extends A> leftTrans, 
        Transformer<? super T, ? extends B> rightTrans) { 
      return Either.right(rightTrans.transform(this.value));
    }

    @Override
    public <A, B> Either<A, B> flatMap(
        Transformer<? super Object, ? extends Either<? extends A, T>> leftTrans, 
        Transformer<? super T, ? extends Either<Object, ? extends B>> rightTrans) {
      @SuppressWarnings("unchecked")
      Either<A, B> transGuy = (Either<A, B>) rightTrans.transform(this.value);
      return transGuy;
    }

    @Override
    public <A> A fold(
        Transformer<? super Object, ? extends A> leftTrans, 
        Transformer<? super T, ? extends A> rightTrans) {
      return rightTrans.transform(this.value);
    }

    @Override
    public <A> Either<? extends Object, ? extends Object> filterOrElse(
        BooleanCondition<? super T> conditioner,
        Transformer<? super T, ? extends A> trans) {
      if (conditioner.test(this.value)) {
        return Either.right(this.value);
      } else {
        return Either.left(trans.transform(this.value));
      }
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      }

      if (other instanceof Right<?>) {
        Right<?> l = (Right<?>) other;

        if (this.value == l.value) {
          return true;
        }

        if (this.value == null || l.value == null) {
          return false;
        }

        return this.value.equals(l.value);
      }

      return false;
    }

    @Override
    public String toString() {
      return String.format("Right[%s]", this.value);
    }
  }
}
