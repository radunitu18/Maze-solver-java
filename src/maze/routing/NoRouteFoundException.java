package maze.routing;
/** Exception class to handle the cases where finding a route was not possible
 *  @@author Nitu Radu Florin
 *  @version 29th April 2021
*/
public class NoRouteFoundException extends Exception{

  /** Builder without arguments, it will display a preset message
  */
  public NoRouteFoundException(){
    super("No route found exception thrown");
  }

  /** Builder with arguments, it will display a custom message
  */
  public NoRouteFoundException(String message){
    super(message);
  }

}
