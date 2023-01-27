import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JFrame {
  private static int l;
  private static boolean[][] b_game;
  private static int[][] b_player;
  private static final int UNKNOWN = -1;
  private static JButton[][] gameButtons;
  private static JButton[] colsButtons;
  private static JButton[] rowsButtons;
  JLabel lifesLabel = new JLabel();
  private static int err;
  
  public Board() {
    l = 0;
    b_game = new boolean[l][l];
    b_player = new int[l][l];
  }

  public Board(int l) {
    this.l = l;
    b_game = new boolean[l][l];
    b_player = new int[l][l];
  }

  public void init() {
    Random rd = new Random();

    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        b_game[i][j] = rd.nextBoolean();
        b_player[i][j] = UNKNOWN;
      }
    }
  }

  public void print() {
    for (int i = 0; i < l; i++) {
      for (int z = 0; z < l; z++) {
        System.out.print("----");
      }

      System.out.println("");

      for (int j = 0; j < l; j++) {
        int z = b_player[i][j];

        if (z == 1) {
          System.out.print("|✅|");
        } else if (z == 0) {
          System.out.print("|❌|");
        } else {
          System.out.print("|❔|");
        }
      }

      System.out.println("");
    }

    for (int z = 0; z < l; z++) {
      System.out.print("----");
    }

    System.out.println("");
  }

  public boolean play(int x, int y) {
    boolean game = b_game[x][y];

    b_player[x][y] = (game == true) ? 1 : 0;

    return game == true;
  }

  public boolean isFinished() {
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        if (b_game[i][j] == true && b_player[i][j] == -1) {
          return false;
        }
      }
    }

    return true;
  }

  private IntSList check(int x, boolean dir) {
    IntSList list = new IntSList();
    int c = 0;

    for (int i = 0; i < l; i++) {
      boolean val = (dir == true) ? b_game[x][i] : b_game[i][x];

      if (val == true) {
        c++;
      }
      if ((val == false || i == l - 1) && i != 0) {
        boolean prec = (dir == true) ? b_game[x][i - 1] : b_game[i - 1][x];

        if ((prec == true || i == l - 1) && c != 0) {
          list = list.cons(c);
        }

        c = 0;
      }
    }

    return list;
  }

  public void printList() {
    System.out.println("Lista di righe");
    for (int i = 0; i < l; i++) {
      IntSList list = check(i, true);
      System.out.println(list.reverse().toString());
    }

    System.out.println("Lista di colonne");
    for (int i = 0; i < l; i++) {
      IntSList list = check(i, false);
      System.out.println(list.reverse().toString());
    }
  }

  public void printRight() {
    for (int i = 0; i < l; i++) {
      for (int z = 0; z < l; z++) {
        System.out.print("----");
      }

      System.out.println("");

      for (int j = 0; j < l; j++) {
        boolean z = b_game[i][j];

        if (z == true) {
          System.out.print("|✅|");
        } else {
          System.out.print("|❌|");
        }
      }

      System.out.println("");
    }

    for (int z = 0; z < l; z++) {
      System.out.print("----");
    }

    System.out.println("");
  }

  private static void showTile(int i, int j){
    ImageIcon icon;
    boolean z = b_game[i][j];
    if (z == true) {
      icon = new ImageIcon("./data/true.png");
    }
    else{
      icon = new ImageIcon("./data/false.png");
    }
    gameButtons[i][j].setText("");
    gameButtons[i][j].setIcon(icon);
    gameButtons[i][j].setEnabled(false);
  }

  private static void showAllTiles(){
    for(int i=0; i<l; i++){
      for(int j=0; j<l; j++){
        showTile(i, j);
      }
    }
  }

  public void showGui(int errors) {
    err = errors;
    JFrame jFrame = new JFrame();
    jFrame.setBounds(100, 100, 600, 600);
    jFrame.setTitle("Nonogram");
    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel cols = new JPanel(new GridLayout(1, l));
    JPanel rows = new JPanel(new GridLayout(l, 1));
    colsButtons = new JButton[l];
    rowsButtons = new JButton[l];
    for(int z=0; z<l; z++){
      colsButtons[z] = new JButton();
      rowsButtons[z] = new JButton();
      /*
       * System.out.println("Lista di righe");
    for (int i = 0; i < l; i++) {
      System.out.println(toString());
    }

    System.out.println("Lista di colonne");
    for (int i = 0; i < l; i++) {
      System.out.println(list.reverse().toString());
    }
       */
      IntSList listCols = check(z, false);
      String col = listCols.reverse().toString();
      colsButtons[z].setText(col);
      colsButtons[z].setEnabled(false);
      IntSList listRows = check(z, true);
      String row = listRows.reverse().toString();
      rowsButtons[z].setText(row);
      rowsButtons[z].setEnabled(false);
      cols.add(colsButtons[z]);
      rows.add(rowsButtons[z]);
    }

    JPanel grid = new JPanel(new GridLayout(l, l));
    gameButtons = new JButton[l][l];

    for (int i = 0; i < l; i++) {
      for (int j = 0; j < l; j++) {
        gameButtons[i][j] = new JButton(i + "-" + j);
        ImageIcon icon = new ImageIcon("./data/unknown.png");
        gameButtons[i][j].setIcon(icon);
        gameButtons[i][j].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JButton b = (JButton) e.getSource();
                String s = b.getText();
                String[] parts = s.split("-");
                int i = Integer.valueOf(parts[0]);
                int j = Integer.valueOf(parts[1]);
                if(play(i, j)==false){
                  err--;
                  lifesLabel.setText("Lifes: "+Integer.toString(err));
                }
                showTile(i, j);
                if(isFinished()){
                  JOptionPane.showMessageDialog(null, "You have won");
                  showAllTiles();
                }
                if(err==0){
                  JOptionPane.showMessageDialog(null, "You loose");
                  showAllTiles();
                }
            }
        });
        grid.add(gameButtons[i][j]);
      }
    }

    JPanel lifes = new JPanel();
    lifesLabel.setText("Lifes: "+Integer.toString(err));
    lifes.add(lifesLabel);

    jFrame.getContentPane().add(rows, BorderLayout.WEST);
    jFrame.getContentPane().add(cols, BorderLayout.NORTH);
    jFrame.getContentPane().add(grid, BorderLayout.CENTER);
    jFrame.getContentPane().add(lifes, BorderLayout.SOUTH);
    jFrame.setVisible(true);
  }
}