package maze;
/** Exception class to handle errors related to mazes with multiple entrances
 *  @@author Nitu Radu Florin
 *  @version 29th April 2021
*/
public class MultipleEntranceException extends InvalidMazeException{

	/** Builder without arguments, it will display a preset message
	*/
	public MultipleEntranceException(){
		super("Multiple Entrances");
	}

	/** Builder with arguments, it will display a custom message
	*/
	public MultipleEntranceException(String message){
		super(message);
	}
}
