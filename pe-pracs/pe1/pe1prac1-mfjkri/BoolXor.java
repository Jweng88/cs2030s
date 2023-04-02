class BoolXor extends Operation {
  public BoolXor(Expression a, Expression b) {
    super(a, b);
  }

  @Override
  public Boolean eval() {
    Object a = this.getFirst().eval();
    Object b = this.getSecond().eval();

    if ((a instanceof Boolean) && (b instanceof Boolean)) {
      Boolean ab = (Boolean) a;
      Boolean bb = (Boolean) b;
      return ab ^ bb;
    }

    throw new InvalidOperandException('^');
  }
}
