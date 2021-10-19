package maze;
import java.io.*;

/** Class to handle operations for objects of type tile
 *  @author Nitu Radu Florin
 *  @version 29th April 2021
*/

public class Tile implements Serializable{
  private Type type;
  private boolean stepped;

/** The builder for the Tile object
  * @param typeIn: Provides the type for the Tile
*/

  public Tile(Type typeIn){
    type = typeIn;
    stepped = false;
  }

/** Returns the type of the tile object
 *  @return The value of the type
*/

  public Type getType(){
    return type;
  }

/** Transforms a char to a Tile object
 * @param c: The character that is provided to be transformed into a Tile object
 * @return A Tile object from the char provided
 *
*/

  public static Tile fromChar(char c){
    Tile tile;
    switch(c){
      case 'x':
        tile = new Tile(Type.EXIT);
        break;
      case 'e':
        tile = new Tile(Type.ENTRANCE);
        break;
      case '#':
        tile = new Tile(Type.WALL);
        break;
      default:
        tile = new Tile(Type.CORRIDOR);
        break;
    }
    return tile;
  }

/** Checks to see if the tile is navigable
 * @return Returns false if the tile is of type WALL, true otherwise
*/

  public boolean isNavigable(){
    if(type == Type.ENTRANCE || type == Type.CORRIDOR || type == Type.EXIT)
      return true;
    else
      return false;
  }

/** Gets the string representation of a Tile
 *  @return Returns a String representation of the tile based on it's type.
*/

  public String toString(){
    switch(type){
      case EXIT:
        return "x";
      case CORRIDOR:
        return ".";
      case ENTRANCE:
        return "e";
      case WALL:
        return "#";
      default:
        return ".";
    }
  }

/** Gets the status of a tile (whether or not it has been stepped on)
 * @return Returns the value of the status
*/

  public boolean getStepStatus(){
    return stepped;
  }

/** On call, it sets the step status to true, marking that the tile has been stepped on
*/

  public void setStepStatus(){
    stepped = true;
  }

  /**
   * Types of tiles
  */

  public enum Type{
    /**
     * Corridor type
    */
    CORRIDOR,
    /**
     * Entrance type
    */
    ENTRANCE,
    /**
     * Exit type
    */
    EXIT,
    /**
     * Wall type
    */
    WALL
  }


}
