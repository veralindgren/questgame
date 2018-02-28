package questgame;

public class Grass extends BasicObject {
  @Override
  public String feedback() {
    return "Nothing interesting on this spot.";
  }

  @Override
  public String description() {
    return "grass";
  }

  @Override
  public int id() {
    return 0;
  }

}
