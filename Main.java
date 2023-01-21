import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner input=new Scanner(System.in);
    int length;
    int errors=3;
    int x, y;

    System.out.print("Inserisci la larghezza della griglia: ");

    length=input.nextInt();
    Board game=new Board(length);

    game.init();

    while(!game.isFinished()&&errors!=0){
      game.print();
      game.printList();

      System.out.print("Selezione la riga (da 0 a "+(length-1)+"): ");
      x=input.nextInt();
      System.out.print("Selezione la colonna (da 0 a "+(length-1)+"): ");
      y=input.nextInt();

      boolean played=game.play(x, y);

      if(played!=true){
        errors--;
      }
    }

    if(errors==0){
      System.out.println("Hai perso! La soluzione giusta è");
    }
    else{
      System.out.println("Hai vinto!");
    }

    game.printRight();

    /*
    Scanner input = new Scanner(System.in);

    // Getting int input
    System.out.print("Enter an integer: ");
    int number = input.nextInt();
    System.out.println("Integer entered " + number);

    // Getting float input
    System.out.print("Enter float: ");
    float myFloat = input.nextFloat();
    System.out.println("Float entered = " + myFloat);
  
    // Getting double input
    System.out.print("Enter double: ");
    double myDouble = input.nextDouble();
    System.out.println("Double entered = " + myDouble);
  
    // Getting String input
    System.out.print("Enter text: ");
    String myString = input.next();
    System.out.println("Text entered = " + myString);
    */
  }
}
