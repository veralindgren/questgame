package questgame;

/* There are four objects in the game.
   But if there were more, most of them would not end the game (but add more interaction, i suppose).
   So they won't bother with implementing interface and return false as well.
  */

public abstract class BasicObject implements GameObject{
  @Override
  public Boolean gameEnd() {
    return false;
  }
}
