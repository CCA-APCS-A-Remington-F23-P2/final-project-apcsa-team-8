import java.util.*;

public class Main{

  public static void main(String args[]){
    //new GameManager();
    Level lev1 = new Level("lvl.txt");
    ArrayList<String> out = lev1.getLevelArray();
    System.out.println(Arrays.toString(out.toArray()));
  }

}