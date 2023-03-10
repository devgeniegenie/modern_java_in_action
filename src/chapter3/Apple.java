package chapter3;

public class Apple {

  private int weight = 0;
  private Color color;

  public Apple(int weight, Color color) {
    this.weight = weight;
    this.color = color;
  }

  public Apple() {
  }

  public Apple(Integer integer) {
  }

  public Apple(Color color, Integer integer) {
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return String.format("Apple{color=%s, weight=%d}", color, weight);
  }

}