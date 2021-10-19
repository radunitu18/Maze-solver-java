package maze.routing;
import maze.*;
import java.util.*;
import java.io.*;

/** Class to handle operations for objects of type RouteFinder.
 *  @author Nitu Radu Florin
 *  @version 29th April 2021
*/

public class RouteFinder implements Serializable{
  private Maze maze;
  private Stack<Tile> route;
  private boolean finished;

  /** The builder for the RouteFinder objects
    * @param mazeIn: Provides the maze attribute to be used for the other functions
  */

  public RouteFinder(Maze mazeIn){
    maze = mazeIn;
    route = new Stack<Tile>();
  }

/** Gets the current maze
 * @return Returns the maze
*/

  public Maze getMaze(){
    return maze;
  }

/** Transforms the Stack route into a list, with its first element being the entrance
 * @return Returns a list of all the elements in the stack ordered from entrance to exit
*/

  public List<Tile> getRoute(){
    List<Tile> list = new ArrayList<Tile>();
    while(route.empty()==false)
    {
      list.add(route.pop());
    }
    Collections.reverse(list);
    for(Tile item : list)
    {
      route.push(item);
    }
    return list;
  }

/** Checks to see if the maze is finished
 *  @return Returns true if the last element in the stack is of type EXIT, and false otherwise
 */

  public boolean isFinished(){
    return this.finished;
  }

/** Loads a maze txt file from memory
 * @param path: The path in memory where the file is stored
 * @return  Returns a RouteFinder object which has the mase at the specified path stored in it, or null if it cannot get the file
*/


  public static RouteFinder load(String path){
    try(ObjectInputStream objStr = new ObjectInputStream(new FileInputStream(path)))
    {
      return (RouteFinder)objStr.readObject();

    }
    catch(FileNotFoundException e){
      System.out.println(e);
    }
    catch(IOException | ClassNotFoundException e){
      System.out.println(e);
    }
    return null;
  }

/** Saves an object to a provided file.
 * @param path: The path where the file is stored, or will be created if it does not already exist
*/

  public void save(String path){
    try(ObjectOutputStream  objOut = new ObjectOutputStream(new FileOutputStream(path));
    )
    {
        objOut.writeObject(this);
    }
    catch(FileNotFoundException e)
    {
      System.out.println(e);
    }
    catch(IOException e){
      System.out.println(e);
    }
  }
/** The methos checks to see if it can move to the next tile. If the tile it moved to is the Exit tile, it will return true, otherwise false
 * @return If the tile it has moved to is the Exit tile, it will return true, otherwise false
*/

  public boolean step(){
    Tile t;
    if(!this.isFinished())
{
    if(route.empty())
    {
      route.push(maze.getEntrance());
      t = route.peek();
      t.setStepStatus();
    }
    else
    {
      t = route.peek();
      Maze.Coordinate coordst = maze.getTileLocation(t);
      if(maze.getAdjacentTile(t, Maze.Direction.EAST).isNavigable() && maze.getAdjacentTile(t, Maze.Direction.EAST).getStepStatus() == false)
      {
        maze.getAdjacentTile(t, Maze.Direction.EAST).setStepStatus();
        route.push(maze.getAdjacentTile(t, Maze.Direction.EAST));
      }
      else
      {
       t  = route.peek();

        if(maze.getAdjacentTile(t, Maze.Direction.NORTH).isNavigable() && maze.getAdjacentTile(t, Maze.Direction.NORTH).getStepStatus() == false)
        {
          maze.getAdjacentTile(t, Maze.Direction.NORTH).setStepStatus();
          route.push(maze.getAdjacentTile(t, Maze.Direction.NORTH));
          }
        else
        {
          t  = route.peek();

            if(maze.getAdjacentTile(t, Maze.Direction.SOUTH).isNavigable() && maze.getAdjacentTile(t, Maze.Direction.SOUTH).getStepStatus() == false)
            {
              maze.getAdjacentTile(t, Maze.Direction.SOUTH).setStepStatus();
              route.push(maze.getAdjacentTile(t, Maze.Direction.SOUTH));
            }
          else
          {
            t  = route.peek();

            if(maze.getAdjacentTile(t, Maze.Direction.WEST).isNavigable() && maze.getAdjacentTile(t, Maze.Direction.WEST).getStepStatus() == false)
            {
              maze.getAdjacentTile(t, Maze.Direction.WEST).setStepStatus();
              route.push(maze.getAdjacentTile(t, Maze.Direction.WEST));
            }
            else
            {
              route.pop();
              if(route.isEmpty())
              {
              try
                {
                  throw new NoRouteFoundException();
                }catch(NoRouteFoundException e)
                {
                  System.out.println(e);
                }
                catch(Exception e1)
                {
                  System.out.println(e1);
                }
              }
            }
          }
        }
      }
    }
    if(route.peek() == maze.getExit())
      {
        finished = true;
        return true;
      }
    else
      return false;
}
return true;
  }

/** Checks whether a file is im the stack
 * @param t: The tile it will look for
 * @return Returns true if the file is in the route, false otherwise
*/

  public boolean isInRoute(Tile t){
    List<Tile> rt= this.getRoute();
    if(rt.contains(t))
      return true;
    return false;
  }

/** Returns a String representation of the current Route
 *  @return A String represenation of the route
*/
  public String toString(){
    String printable = "";
    int rows = 0;
    for(int i=0;i<maze.getTiles().size();i++)
    {
      for(int j=0; j< maze.getTiles().get(i).size(); j++)
      {
        if(maze.getTiles().get(i).get(j).toString() == "#")
          printable = printable + "#";
        else
          if((maze.getTiles().get(i).get(j).toString() == "." || maze.getTiles().get(i).get(j).toString() == "e") && this.isInRoute(maze.getTiles().get(i).get(j))==false && maze.getTiles().get(i).get(j).getStepStatus() == true)
              printable = printable + "-";
          else
              if((maze.getTiles().get(i).get(j).toString() == "." || maze.getTiles().get(i).get(j).toString() == "e") && this.isInRoute(maze.getTiles().get(i).get(j)) == true)
              printable = printable + "*";
              else
                if(maze.getTiles().get(i).get(j).toString() == "." && this.isInRoute(maze.getTiles().get(i).get(j)) == false && maze.getTiles().get(i).get(j).getStepStatus() == false)
                printable = printable + ".";
                else
                  if(maze.getTiles().get(i).get(j).toString() == "e" &&
                  this.isInRoute(maze.getTiles().get(i).get(j))== false)
                  printable= printable + "e";
                  else
                    if(maze.getTiles().get(i).get(j).toString() == "x" &&
                    this.isInRoute(maze.getTiles().get(i).get(j))== false)
                    printable= printable + "x";
      }
      rows ++;
      printable = printable + "\n";
    }
    if(rows <=2){
      throw new IndexOutOfBoundsException("Maze is too small");

    }
    return printable;
  }
}
