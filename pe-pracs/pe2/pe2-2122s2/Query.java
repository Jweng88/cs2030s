/**
 * CS2030S PE2 Question 2
 * AY21/22 Semester 2
 *
 * @author A0000000X
 */
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.Predicate;

class Query {
    public static <T,S> Stream<Map.Entry<T, S>> getFilteredByKey(
        Map<T, S> table, Predicate<T> p) { 
      return table.entrySet().stream()
        .filter(v -> p.test(v.getKey())); 
    }

    public static Stream <Integer> getIdsFromName(
        Map<String, List<Integer>> table, String name) {
      return Query.getFilteredByKey(table, x -> x == name)
        .flatMap(entry -> entry.getValue().stream());
    }

    public static Stream<Double> getCostsFromIDs(
        Map<Integer, Double> table, Stream<Integer> ids) {
      return ids.map(id -> table.get(id))
        .filter(cost -> cost != null);
    }

    public static Stream<String> allCustomerCosts(
        Map<String, List<Integer>> customers, Map<Integer, Double> sales) {
      return customers.keySet().stream()
        .flatMap(
            name -> getCostsFromIDs(sales, getIdsFromName(customers, name))
                      .map(cost -> String.format("%s: %s", name, cost)));
    }

    public static Stream<String> totaledCustomerCosts(
         Map<String, List<Integer>> customers, Map<Integer, Double> sales) {
      return customers.keySet().stream()
        .map(
            name -> String.format("%s: %s", name, 
              getCostsFromIDs(sales, getIdsFromName(customers, name))
                .reduce(0.0, (total, cost) -> total + cost)));
    }
}

