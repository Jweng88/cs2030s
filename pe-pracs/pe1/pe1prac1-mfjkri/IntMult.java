class IntMult extends Operation {
  public IntMult(Expression a, Expression b) {
    super(a, b);
  }

  @Override
  public Integer eval() {
    Object a = this.getFirst().eval();
    Object b = this.getSecond().eval();

    if ((a instanceof Integer) && (b instanceof Integer)) {
      Integer ai = (Integer) a;
      Integer bi = (Integer) b;
      return ai * bi;
    }

    throw new InvalidOperandException('*');
  }
}
