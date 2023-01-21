import java.util.Random;

public class Board{
  private static int l;
  private static boolean[][] b_game;
  private static int[][] b_player;
  private static final int UNKNOWN=-1;

  public Board(){
    l=0;
    b_game=new boolean[l][l];
    b_player=new int[l][l];
  }

  public Board(int l){
    this.l=l;
    b_game=new boolean[l][l];
    b_player=new int[l][l];
  }

  public void init(){
    Random rd=new Random();
    
    for(int i=0; i<l; i++){
      for(int j=0; j<l; j++){
        b_game[i][j]=rd.nextBoolean();
        b_player[i][j]=UNKNOWN;
      }
    }
  }

  public void print(){
    for(int i=0; i<l; i++){
      for(int z=0; z<l; z++){
        System.out.print("----");
      }
      
      System.out.println("");
      
      for(int j=0; j<l; j++){
        int z=b_player[i][j];
        
        if(z==1){
          System.out.print("|✅|");
        }
        else if(z==0){
          System.out.print("|❌|");
        }
        else{
          System.out.print("|❔|");
        }
      }
      
      System.out.println("");
    }

    for(int z=0; z<l; z++){
      System.out.print("----");
    }
    
    System.out.println("");
  }

  public boolean play(int x, int y){
    boolean game=b_game[x][y];

    b_player[x][y]=(game==true)?1:0;
    
    return game==true;
  }

  public boolean isFinished(){
    for(int i=0; i<l; i++){
      for(int j=0; j<l; j++){
        if(b_game[i][j]==true&&b_player[i][j]==-1){
          return false;
        }
      }
    }

    return true;
  }

  private IntSList check(int x, boolean dir){
    IntSList list=new IntSList();
    int c=0;

    for(int i=0; i<l; i++){
      boolean val=(dir==true)?b_game[x][i]:b_game[i][x];
      
      if(val==true){
        c++;
      }
      if((val==false||i==l-1)&&i!=0){
        boolean prec=(dir==true)?b_game[x][i-1]:b_game[i-1][x];
        
        if((prec==true||i==l-1)&&c!=0){
          list=list.cons(c);
        }
        
        c=0;
      }
    }
    
    return list;
  }

  public void printList(){
    System.out.println("Lista di righe");
    for(int i=0; i<l; i++){
      IntSList list=check(i, true);
      System.out.println(list.reverse().toString());
    }

    System.out.println("Lista di colonne");
    for(int i=0; i<l; i++){
      IntSList list=check(i, false);
      System.out.println(list. reverse().toString());
    }
  }

  public void printRight(){
    for(int i=0; i<l; i++){
      for(int z=0; z<l; z++){
        System.out.print("----");
      }
      
      System.out.println("");
      
      for(int j=0; j<l; j++){
        boolean z=b_game[i][j];
        
        if(z==true){
          System.out.print("|✅|");
        }
        else{
          System.out.print("|❌|");
        }
      }
      
      System.out.println("");
    }

    for(int z=0; z<l; z++){
      System.out.print("----");
    }
    
    System.out.println("");
  }
}