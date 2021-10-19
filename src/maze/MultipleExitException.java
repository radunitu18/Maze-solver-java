package maze;
/** Exception class to handle errors related to mazes with multiple exits
 *  @@author Nitu Radu Florin
 *  @version 29th April 2021
*/
public class MultipleExitException extends InvalidMazeException{

	/** Builder without arguments, it will display a preset message
	*/

	public MultipleExitException(){
		super("Multiple Exits");
	}

	/** Builder with arguments, it will display a custom message
	*/
	public MultipleExitException(String message){
		super(message);
	}
}
