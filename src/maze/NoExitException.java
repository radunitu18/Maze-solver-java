package maze;
/** Exception class to handle errors related to mazes with no exits
 *  @@author Nitu Radu Florin
 *  @version 29th April 2021
*/
public class NoExitException extends InvalidMazeException{


	/** Builder without arguments, it will display a preset message
	*/
	public NoExitException(){
		super("No Exit");
	}
	/** Builder without arguments, it will display a preset message
	*/

		/** Builder with arguments, it will display a custom message
		*/
	public NoExitException(String message){
		super(message);
	}
}
