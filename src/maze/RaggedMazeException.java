package maze;
/** Exception class to handle errors related to ragged mazes (mazes that have rows of different lengths)
 *  @@author Nitu Radu Florin
 *  @version 29th April 2021
*/
public class RaggedMazeException extends InvalidMazeException{
	/** Builder without arguments, it will display a preset message
	*/
	public RaggedMazeException(){
		super("Ragged Maze");
	}
	/** Builder with arguments, it will display a custom message
	*/
	public RaggedMazeException(String message){
		super(message);
	}
}
