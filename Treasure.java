public class Treasure implements GameObject {
  @Override
  public String feedback() {
    return "You found a treasure. Cool. You won.";
  }

  @Override
  public Boolean gameEnd() {
    return true;
  }

  @Override
  public String description() {
    // User bamboozled!
    return "grass";
  }

  @Override
  public int id() {
    return 3;
  }

}
