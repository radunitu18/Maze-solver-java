package maze;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.*;
import java.io.*;
/** Class to handle objects of type Maze
 *  @author Nitu Radu Florin
 *  @version 29th April 2021
*/

public class Maze implements Serializable{
  private Tile entrance;
  private Tile exit;
  private List<List<Tile>> tiles = new ArrayList<List<Tile>>();

/** The builder for Maze objects
*/

   private Maze(){
    this.entrance = entrance;
    this.exit = exit;
    this.tiles = tiles;
  }

/** Creates a Maze object from an external file. It also checks for unchecked errors
 *  @param path: The path to the file to be transformed into a maze
 *  @return Returns the maze object created from the external file

*/
  public static Maze fromTxt(String path) throws FileNotFoundException{
    int entranceCount = 0;
    int exitCount = 0;
    Scanner file = new Scanner(new File(path));
    Maze newMaze = new Maze();
    Tile t2 = new Tile(Tile.Type.WALL);

    while(file.hasNextLine())
    {
      ArrayList<Tile> line = new ArrayList<Tile>();
      String nLine = file.nextLine();
      char[] characters = nLine.toCharArray();

      for(int i = 0; i < characters.length; i++)
      {
          Tile t1 = new Tile(Tile.Type.WALL);
          t1 = t1.fromChar(characters[i]);
          line.add(t1);
      }

      newMaze.getTiles().add(line);
    }
    for(int i=0;i<newMaze.getTiles().size();i++)
    {
      for(int j=0;j<newMaze.getTiles().get(i).size();j++)
      {
        if(newMaze.getTiles().get(i).get(j).getType() == Tile.Type.ENTRANCE)
        {
          entranceCount +=1;


            newMaze.setEntrance(newMaze.getTiles().get(i).get(j));

        }
        if(newMaze.getTiles().get(i).get(j).getType() == Tile.Type.EXIT)
        {
          exitCount +=1;

          newMaze.setExit(newMaze.getTiles().get(i).get(j));
        }
      }
    }


    if(newMaze.getExit() == null)
    {
      try{throw new NoExitException();}
      catch(NoExitException e)
      {
        System.out.println(e);
      }
      catch(Exception e1)
      {
        System.out.println("invalid maze, throw manual error for no exit");
      }
    }
    if(newMaze.getEntrance() == null)
    {
      try{throw new NoEntranceException();}
      catch(NoEntranceException e)
      {
        System.out.println(e);
      }
      catch(Exception e){
        System.out.println("invalid maze, throw manual error for no entrance");
      }
    }


    return newMaze;
  }

/** On call it returns the exit tile
 *  @return Return the exit tile
*/

  public Tile getExit(){
    this.exit = null;
    for(int i = 0; i < tiles.size(); i++)
    {
      for(int j = 0; j < tiles.get(i).size(); j++)
      {
        if(tiles.get(i).get(j).getType() == Tile.Type.EXIT)
          this.exit = tiles.get(i).get(j);
      }
    }
    return this.exit;
  }

  /** On call it returns the entrance tile
   *  @return Return the entrance tile
  */

  public Tile getEntrance(){
    this.entrance = null;
    for(int i = 0; i < tiles.size(); i++)
    {
      for(int j = 0; j < tiles.get(i).size(); j++)
      {
        if(tiles.get(i).get(j).getType() == Tile.Type.ENTRANCE)
          this.entrance = tiles.get(i).get(j);
      }
    }
    return this.entrance;
  }

  /** On call it returns the list of all tiles
   *  @return Returns the list of all the tiles
  */

  public List<List<Tile>> getTiles(){
    return this.tiles;
  }

/** Sets the entrance tile, only works if called from inside a function, as it is private
 *  @param t: The tile to be set as entrance
*/

  private void setEntrance(Tile t){
    if(this.entrance == null && this.getTiles().contains(t))
      this.entrance = t;
    else
      try{throw new MultipleEntranceException();}
      catch(MultipleEntranceException e)
      {
        System.out.println(e);
      }
      catch(Exception e1)
      {
        System.out.println(e1);
      }

  }

/** Sets the exit tile, only works if called from inside a function, as it is private
 *  @param t: The tile to be set as exit
*/

  private void setExit(Tile t){
    if(this.exit == null  && this.getTiles().contains(t))
      this.exit = t;
    else
      try{throw new MultipleExitException();}
      catch(MultipleExitException e)
      {
        System.out.println(e);
      }
      catch(Exception e1)
      {
        System.out.println(e1);
      }
  }

/** Gets the tile in the specified direction of the specified tiles
 *  @param t: The original tile
 *  @param d: The direction to search the tile in
 *  @return Returns the tile from the specified direction
*/

  public Tile getAdjacentTile(Tile t, Direction d){
    Tile t1 = new Tile(Tile.Type.WALL);
    Coordinate coords;
    if(d == Direction.SOUTH)
    {
      coords = this.getTileLocation(t);
      /*if(tiles.get(coords.getX()-1).get(coords.getY()) != null)*/
      if(coords.getX()!=tiles.size()-1)
        return tiles.get(tiles.size()  - coords.getY()).get(coords.getX());
    }
    if(d == Direction.NORTH)
    {
      coords = this.getTileLocation(t);
      /*if(tiles.get(coords.getX()+1).get(coords.getY()) != null)*/
      if(coords.getX()!=0)
        return tiles.get(tiles.size() - 2 - coords.getY()).get(coords.getX());
    }
    if(d == Direction.WEST)
    {
      coords = this.getTileLocation(t);
      /*if(tiles.get(coords.getX()).get(coords.getY()-1) != null)*/
      if(coords.getY() != 0)
        return tiles.get(tiles.size() -1- coords.getY()).get(coords.getX()-1);
    }
    if(d == Direction.EAST)
    {
      coords = this.getTileLocation(t);
      /*if(tiles.get(coords.getX()).get(coords.getY()+1) != null)*/
      if(coords.getY() != tiles.get(0).size()-1)
        return tiles.get(tiles.size() -1-coords.getY()).get(coords.getX()+1);
    }
    return t1;
  }

/** Gets the tile at the specified coordinates
 *  @param coords: The coordinates where to look for the tiles
 *  @return Returns the tile at the specified location
*/

  public Tile getTileAtLocation(Coordinate coords){
    return tiles.get(tiles.size()-1-coords.getY()).get(coords.getX());
  }

/** Gets the coordinates of a privided tile
 * @param t: The tile for which it will return the coordinates
 * @return The coordinates of the provided tile
*/

  public Coordinate getTileLocation(Tile t){
    Maze.Coordinate coords = new Maze.Coordinate(0, 0);
    for(int i=0; i < tiles.size(); i++)
    {
      for(int j=0; j < tiles.get(i).size(); j++)
      {
        if(t == tiles.get(i).get(j))
          coords = new Maze.Coordinate(j,tiles.size()-1-i);
      }
    }
    return coords;
  }

/** Creates a string representation of the current maze
 *  @return Returns a String which is a representation of the maze
*/

  public String toString(){
    String printable = "";
    for(int i=0; i< tiles.size(); i++)
        {
            for(int j = 0; j< tiles.get(i).size(); j++)
            {
                if(tiles.get(i).get(j).toString()=="e")
                  printable = printable + "e";
                else
                  if(tiles.get(i).get(j).toString()=="x")
                    printable = printable + "x";
                  else
                    if(tiles.get(i).get(j).toString() == "#")
                      printable = printable + "#";
                    else
                      printable = printable + ".";


            }
        printable = printable + "\n";
      }
    return printable;
  }



  /** Class to handle operations for objects of type Coordinate
   *  @author Nitu Radu Florin
   *  @version 29th April 2021
  */

  public class Coordinate{
    private int x;
    private int y;

  /** Builder for the Coordinate objects
   * @param xIn: The x coordinate
   * @param yIn  The y coordinate
  */

    public Coordinate(int xIn, int yIn){
      x = xIn;
      y = yIn;
    }

  /** Getter for the x Coordinate
   *  @return Returns the value of the x coordinate
  */

    public int getX(){
      return x;
    }

  /** Getter for the y Coordinate
   *  @return Returns the value of the y coordinate
  */

    public int getY(){
      return y;
    }
  /** Creates a String representation of the coordinates of the object
   *  @return A String of the format (x,y) of the coordinates
  */

    public String toString(){
      return "(" + x + ", " + y + ")";
    }
  }







  /**
   * Directions that can be used
  */

  public enum Direction{
    /**
     * North
     */
    NORTH,
    /**
     * South
     */
    SOUTH,
    /**
     * East
     */
    EAST,
    /**
     * West
     */
    WEST
  }

}
