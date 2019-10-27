import java.util.HashMap;
import java.util.Map;

public class Game {
  private GameObject[][] map = new GameObject[10][10];

  //users position on the map
  private int x = 5;
  private int y = 5;

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }


  public void setMap(GameObject[][] map) {
    this.map = map;
  }

  public GameObject[][] getMap() {
    return map;
  }


  public void fillMap() {
    fillRandom(8, new Enemy());
    fillRandom(10, new Tree());
    fillRandom(1, new Treasure());

    //create Grass on remained empty elements
    for (int rows = 0; rows < map.length; rows++) {
      for (int columns = 0; columns < map[rows].length; columns++) {
        if (map[rows][columns] == null) {
          map[rows][columns] = new Grass();
        }
      }
    }
  }

  private void fillRandom(int count, GameObject object) {
    int counter = 0;
    while (counter < count) {
      int a = randomNumber();
      int b = randomNumber();
      if (map[a][b] == null) {
        map[a][b] = object;
        counter++;
      }
    }
  }

  public Map<String, GameObject> getSurroundings() {
    Map<String, GameObject> result = new HashMap<>();
    if (y != 0) {
      result.put("n", map[y - 1][x]);
    }
    if (y != 9) {
      result.put("s", map[y + 1][x]);
    }
    if (x != 0) {
      result.put("w", map[y][x - 1]);
    }
    if (x != 9) {
      result.put("e", map[y][x + 1]);
    }
    return result;
  }

  public boolean move(String direction) {
    if (direction.equals("n") && y != 0) {
      y = y - 1;
      return true;
    }
    if (direction.equals("s") && y != 9) {
      y = y + 1;
      return true;
    }
    if (direction.equals("w") && x != 0) {
      x = x - 1;
      return true;
    }
    if (direction.equals("e") && x != 9) {
      x = x + 1;
      return true;
    }
    return false;
  }

  public static int randomNumber() {
    return (int) (Math.random() * 10);
  }

  public boolean check() {
    /* this code will print the map

    for (int yy = 0; yy < map.length; yy++) {
      System.out.println();
      for (int xx = 0; xx < map[yy].length; xx++) {
        if (x != xx || y != yy) {
          System.out.print(map[yy][xx].getClass().getSimpleName().substring(0, 1) + " ");
        } else {
          System.out.print("@ ");
        }
      }
    }
    System.out.println();
     */

    // checking current element on the map
    GameObject gameObject = map[y][x];
    System.out.println(gameObject.feedback());
    if (gameObject.gameEnd()) {
      return false;
    }
    if (gameObject instanceof Tree) {
      System.out.println(treasurePosition());
    }
    // information about surrounding elements
    Map<String, GameObject> surroundings = getSurroundings();
    for (Map.Entry<String, GameObject> entry : surroundings.entrySet()) {
      String direction = entry.getKey();
      GameObject object = entry.getValue();
      System.out.println("There is a " + object.description() + " in the " + direction);
    }
    return true;
  }

  // Method divides a map in four parts in order to get treasure's position.
  public String treasurePosition() {
    int[] coordinates = new int[2];
    GameObject[][] map = this.getMap();
    for (int y = 0; y < map[0].length; ++y) {
      for (int x = 0; x < map[1].length; ++x) {
        if (map[y][x] instanceof Treasure) {
          coordinates[0] = y;
          coordinates[1] = x;
        }
      }
    }
    if (coordinates[0] < map[0].length / 2 && coordinates[1] < map[1].length / 2) {
      return "The treasure is in north-west of the map.";
    } else if (coordinates[0] >= map[0].length / 2 && coordinates[1] < map[1].length / 2) {
      return "The treasure is in south-west of the map.";
    } else if (coordinates[0] >= map[0].length / 2 && coordinates[1] >= map[1].length / 2) {
      return "The treasure is in south-east of the map.";
    } else if (coordinates[0] < map[0].length / 2 && coordinates[1] >= map[1].length / 2) {
      return "The treasure is in north-east of the map.";
    }
    return null;
  }
}

