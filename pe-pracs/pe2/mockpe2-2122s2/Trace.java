
/**
 * @author BLUBLUBLU
 */
import java.util.ArrayList;
import java.util.List;

class Trace<T> {
  private T variable;
  private List<T> changes;

  public Trace(T variable, List<T> changes) {
    this.variable = variable;
    this.changes = changes;
  }

  @SafeVarargs
  public static <T> Trace<T> of(T... values) {
    ArrayList<T> changes = new ArrayList<>();
    for (int i = 1; i < values.length; i++) {
      changes.add(values[i]);
    }

    return new Trace<T>(values[0], changes);
  }

  public T get() {
    return this.variable;
  }

  public List<T> history() {
    return this.changes;
  }

  public Trace<T> back(int i) {
    if (i == 0 || this.changes.size() == 0) {
      return new Trace<T>(this.variable, this.changes);
    }

    int lastIndex = this.changes.size() - 1;
    ArrayList<T> newChanges = new ArrayList<T>(this.changes);
    T newVariable = newChanges.get(lastIndex);

    newChanges.remove(lastIndex);

    return new Trace<T>(newVariable, newChanges).back(i - 1);
  }

  public Trace<T> map(Transformer<? super T, ? extends T> mapper) {
    ArrayList<T> newChanges = new ArrayList<T>(this.changes);
    T newVariable = mapper.transform(this.variable);

    newChanges.add(this.variable);

    return new Trace<T>(newVariable, newChanges);
  }

  public Trace<T> flatMap(Transformer<? super T, ? extends Trace<? extends T>> mapper) {
    ArrayList<T> newChanges = new ArrayList<T>(this.changes);

    @SuppressWarnings("unchecked")
    Trace<T> transGuy = (Trace<T>) mapper.transform(this.variable);

    newChanges.add(this.variable);
    newChanges.addAll(transGuy.changes);

    return new Trace<T>(transGuy.variable, newChanges);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Trace<?>) {
      Trace<?> t = (Trace<?>) obj;

      if (this.variable == t.variable && this.changes == t.changes) {
        return true;
      }

      if (this.variable == null || t.variable == null || this.changes == null || this.changes == null) {
        return false;
      }

      return this.variable.equals(t.variable) && this.changes.equals(t.changes);
    }

    return false;
  }
  }
