/**
 * CS2030S PE1 Question 2
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */
class ArrayStack<T> implements Stack<T> {
  private int pointer;
  private int size;
  private final T[] stack;

  public ArrayStack(int size) {
    this.size = size;

    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[size];
    this.stack = temp;
  }

  @Override
  public T pop() {
    if (this.pointer == 0) {
      return null;
    }

    int newPointer = this.pointer - 1;
    T item = this.stack[newPointer];
    this.stack[newPointer] = null;
    this.pointer = newPointer;
    return item;
  }

  @Override
  public void push(T item) {
    if (this.pointer == this.size) {
      return;
    }

    this.stack[this.pointer] = item;
    this.pointer++;
  }

  @Override
  public int getStackSize() {
    return this.pointer;
  }

  @Override
  public String toString() {
    String res = "Stack: ";
    for (int i = 0; i < this.pointer; i++) {
      res += this.stack[i] + " ";
    }
    return res;
  }

  public static <U> ArrayStack<U> of(U[] items, int maxSize) {
    ArrayStack<U> newStack = new ArrayStack<U>(maxSize);
    for (int i = 0; i < java.lang.Math.min(items.length, maxSize); i++) {
      newStack.push(items[i]);
    }
    return newStack;
  }

  public void pushAll(ArrayStack<? extends T> other) {
    while (other.getStackSize() > 0) {
      this.push(other.pop());
    }
  }

  public void popAll(ArrayStack<? super T> other) {
    while (this.getStackSize() > 0) {
      other.push(this.pop());
    }
  }
}


