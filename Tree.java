package questgame;

public class Tree extends BasicObject {
  @Override
  public String feedback() {
    return "You see a tree.";
  }

  @Override
  public String description() {
    return "tree";
  }

  @Override
  public int id() {
    return 2;
  }

}
