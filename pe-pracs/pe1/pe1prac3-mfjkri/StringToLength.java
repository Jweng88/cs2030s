class StringToLength implements Transformer<String, Integer> {
  @Override
  public Integer transform(String a) {
    return a.length();
  }
}
