import maze.*;
import maze.routing.*;
import java.io.FileNotFoundException;
public class MazeDriver {
    public static void main(String args[]) throws FileNotFoundException  {
      Maze m1 = new Maze();
      m1.fromTxt("../resources/mazes/maze1.txt");
      Tile ext = m1.getEntrance();
      Tile s1 = m1.getAdjacentTile(ext, Direction.EAST);
      RouteFinder r1 = new RouteFinder(m1);
      System.out.println(m1.getExit());
      System.out.println(m1.getTileLocation(ext).toString());
      System.out.println(m1.toString());
      System.out.println(s1.toString());
      System.out.println(r1.toString());
    }
}
