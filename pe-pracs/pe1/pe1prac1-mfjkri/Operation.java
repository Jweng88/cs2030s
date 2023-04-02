/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */

abstract class Operation implements Expression {
  private final Expression a;
  private final Expression b;

  public Operation(Expression a, Expression b) {
    this.a = a;
    this.b = b;
  }

  public static Operation of(char op, Expression a, Expression b) {
    if (op == '*') {
      return new IntMult(a, b);
    } else if (op == '+') {
      return new StrConcat(a, b);
    } else if (op == '^') {
      return new BoolXor(a, b);
    }

    return null;
  }

  public Expression getFirst() {
    return this.a;
  }

  public Expression getSecond() {
    return this.b;
  }
}
