package questgame;

import java.io.*;

public class Files {

  public static void write(Game game) throws IOException {

    int[][] intArray = objectToIntArray(game);

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < intArray.length; i++) {
      for (int j = 0; j < intArray.length; j++) {
        // Append to the output string
        builder.append(intArray[i][j] + "");
        // If this is not the last row element
        if (j < intArray.length - 1) {
          builder.append(",");
        }
      }
      builder.append("\n"); // Append new line at the end of the row
    }

    // Add user position
    builder.append(game.getY());
    builder.append(",");
    builder.append(game.getX());

    // Creating .txt file in the current folder
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("questgame.txt"))) {
      writer.write(builder.toString());
    }

    System.out.println("Your game was saved.");
  }

  public static Game load() throws IOException {
    Game game = new Game();
    GameObject[][] board = new GameObject[game.getMap()[0].length][game.getMap()[1].length];

    int[][] intBoard = new int[game.getMap()[0].length][game.getMap()[1].length];
    try (BufferedReader reader = new BufferedReader(new FileReader("questgame.txt"))) {
      String line = "";
      int row = 0;
      while ((line = reader.readLine()) != null) {
        String[] columns = line.split(",");

        // If row number is bigger than the map size, than this row contains users coordinates
        if (row > game.getMap()[0].length - 1) {
          game.setY(Integer.parseInt(columns[0]));
          game.setX(Integer.parseInt(columns[1]));
        } else {
          int col = 0;
          for (String c : columns) {
            intBoard[row][col] = Integer.parseInt(c);
            col++;
          }
          row++;
        }
      }
    }

    // Creating objects from their id
    for (int rows = 0; rows < intBoard.length; rows++) {
      for (int columns = 0; columns < intBoard[rows].length; columns++) {
        int id = intBoard[rows][columns];
        board[rows][columns] = GameObject.getObject(id);
      }
    }
    game.setMap(board);
    System.out.println("Your game was loaded.");
    return game;
  }

  // Creating int array with the help of objects id numbers
  public static int[][] objectToIntArray(Game game) {
    GameObject[][] array = game.getMap();
    int[][] intArray = new int[game.getMap()[0].length][game.getMap()[1].length];
    for (int rows = 0; rows < array.length; rows++) {
      for (int columns = 0; columns < array[rows].length; columns++) {
        intArray[rows][columns] = array[rows][columns].id();
      }
    }
    return intArray;
  }
}
