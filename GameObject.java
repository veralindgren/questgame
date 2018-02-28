package questgame;

import java.util.ArrayList;


public interface GameObject {
  String feedback();
  Boolean gameEnd();
  String description();
  int id();

  static GameObject getObject(int id){
    ArrayList<GameObject> objects = new ArrayList<>();
    objects.add(new Grass());
    objects.add(new Enemy());
    objects.add(new Tree());
    objects.add(new Treasure());

    for (GameObject object : objects) {
      if (object.id() == id) {
        return object;
      }
    }
    return null;
  }
}
