package maze;

/** Exception class to handle invalid mazes
 *  @@author Nitu Radu Florin
 *  @version 29th April 2021
*/

public class InvalidMazeException extends Exception{

/** Builder without arguments, it will display a preset message
*/
  public InvalidMazeException(){
    super("Invalid maze");
  }

/** Builder with arguments, it will display a custom message
*/
  public InvalidMazeException(String message){
    super(message);
  }
}
