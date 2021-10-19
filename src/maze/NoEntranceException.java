package maze;
/** Exception class to handle errors related to mazes with no entrances
 *  @@author Nitu Radu Florin
 *  @version 29th April 2021
*/
public class NoEntranceException extends InvalidMazeException{

	/** Builder without arguments, it will display a preset message
	*/
	public NoEntranceException(){
		super("No Entrance");
	}

	/** Builder with arguments, it will display a custom message
	*/
	public NoEntranceException(String message){
		super(message);
	}
}
