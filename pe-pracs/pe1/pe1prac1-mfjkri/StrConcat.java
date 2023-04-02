class StrConcat extends Operation {
  public StrConcat(Expression a, Expression b) {
    super(a, b);
  }

  @Override
  public String eval() {
    Object a = this.getFirst().eval();
    Object b = this.getSecond().eval();

    if ((a instanceof String) && (b instanceof String)) {
      String as = (String) a;
      String bs = (String) b;
      return as + bs;
    }

    throw new InvalidOperandException('+');
  }
}
