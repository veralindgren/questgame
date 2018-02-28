package questgame;

public class Enemy implements GameObject {
  @Override
  public String feedback() {
    return "Big scary monster from your nightmares finally ate you. You lost.";
  }

  @Override
  public Boolean gameEnd() {
    return true;
  }

  @Override
  public String description() {
    return "enemy";
  }

  @Override
  public int id() {
    return 1;
  }

}
