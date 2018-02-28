package questgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
Ett spel där man vandrar över kartan och försöker att hitta en skatt.
Man kan spara och ladda sina framsteg.
_____________________________________________________________________
För att hitta problem i koden printade jag ut kartan för mig själv.
Med printade kartan blir programmet väldigt åskådligt.
(Koden för att göra det finns uppe i Game.check() metoden.)
_____________________________________________________________________
Förbätringar:
* Strukturera koden i Main.main() så att while loopen inte är "spaghetti-code".
  Alltså skriva mer metoder i Game klassen som följer spelet.
  Eller en ny klass som hanterar användarens inputs.

* Att varje träd ger information om skatten relativt sin position.
  (Så som det funkar nu så ger alla träd samma meddelandet).

* Game.getSurroundings() och Game.move() duplicerar samma kod egentligen.
  Om riktningar inte var strings utan enum eller objects, det vore mer elegant lösning.
_____________________________________________________________________
 */

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Game game = new Game();
    game.fillMap();

    System.out.println("Your goal is to find the treasure. It's hidden somewhere in the grass. " +
        "If you meet an enemy, you die. A tree will give you a hint about treasure's position on a map.");

    System.out.println("Type any key to start or type load to load your game. Controls: n for north, s for south, w for west, e for east, save for save, exit for exit, load for load");
    String input = reader.readLine();
    if (input.equals("load")) {
      game = Files.load();
    }
    if (input.equals("exit")){
      return;
    }
    while (true) {
      if (!game.check()) {
        System.out.println("That's it!");
        break;
      }
      System.out.println("Where will you go?");
      String nextMove = reader.readLine();
      if (nextMove.equals("save")) {
        Files.write(game);
      } else if (nextMove.equals("exit")) {
        System.out.println("Wanna save? y/n");
        String nextAction = reader.readLine();
        if (nextAction.equals("y")) {
          Files.write(game);
        }
        break;
      } else {
        if (Arrays.asList("n", "s", "w", "e").contains(nextMove)) {
          if (!game.move(nextMove)) {
            System.out.println("You reached a wall. Try another direction");
          }
        } else {
          System.out.println("Wrong command.");
        }
      }
    }
  }
}


