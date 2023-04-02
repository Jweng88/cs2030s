/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Fikri (Lab 12A)
 */
class DivisibleBy implements BooleanCondition<Integer> {
  private final Integer x;
  
  public DivisibleBy(Integer x) {
    this.x = x;
  }

  @Override
  public boolean test(Integer y) {
    return y % x == 0;
  }
}

