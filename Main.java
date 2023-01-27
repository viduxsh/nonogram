import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try (Scanner gameType = new Scanner(System.in)) {
      System.out.println("Choose game mode (t: terminal, g: gui):");
      String game = gameType.nextLine();

      if (game.equals("t")) {
        terminalGame();
      } else if (game.equals("g")) {
        guiGame();
      } else {
        System.out.println("No mode choosen, exiting");
      }
    }
  }

  public static void terminalGame() {
    try (Scanner input = new Scanner(System.in)) {
      int length;
      int errors = 3;
      int x, y;

      System.out.print("Enter the size of the grid: ");

      length = input.nextInt();
      Board game = new Board(length);

      game.init();

      while (!game.isFinished() && errors != 0) {
        game.print();
        game.printList();

        System.out.print("Select the row (from 0 to" + (length - 1) + "): ");
        x = input.nextInt();
        System.out.print("Select the column (from 0 to" + (length - 1) + "): ");
        y = input.nextInt();

        boolean played = game.play(x, y);

        if (played != true) {
          errors--;
        }
      }

      if (errors == 0) {
        System.out.println("You lost! The right solution is");
      } else {
        System.out.println("You won!");
      }

      game.printRight();
    }
  }

  public static void guiGame() {
    try (Scanner input = new Scanner(System.in)) {
      int length;
      int errors = 3;

      System.out.print("Enter the size of the grid: ");

      length = input.nextInt();
      Board game = new Board(length);

      game.init();
      game.showGui(errors);
    }
  }
}
