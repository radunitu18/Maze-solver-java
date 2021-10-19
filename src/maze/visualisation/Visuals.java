package maze.visualisation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import java.util.*;
import maze.*;
import maze.routing.RouteFinder;
import javafx.scene.paint.Color;

/** Class to handle visual representation of tile elements
 *  @author Nitu Radu Florin
 *  @version 29th April 2021
*/

public class Visuals extends Application{



  public void start(Stage stage){}
  /** Gets a rectangle of different colours for each situation of tiles
   * @param t: The tile that is used to decide the colour
   * @param rf: Used to check the if a tile is in the route.
   * @return If the tile is of type wall, it returns a black square. If the tile is an exit it returns a green square. If a tile is navigable and is in route, it returns a yellow square, if a tile isnot in route and it is navigable, it returns a red square
  */

  public static Rectangle getVisualRepresentation(Tile t, RouteFinder rf){

    Rectangle r = new Rectangle(50,50);

    if(t.getType() == Tile.Type.WALL)
      r.setFill(Color.BLACK);
    else
      if(t.getType() == Tile.Type.EXIT)
        r.setFill(Color.GREEN);
      else
        if(t.getStepStatus() == false)
          r.setFill(Color.WHITE);
        else
          if(t.getStepStatus() == true && rf.isInRoute(t)== false)
            r.setFill(Color.RED);
          else
            if(t.getStepStatus() == true && rf.isInRoute(t)== true)
              r.setFill(Color.YELLOW);
      return r;
  }



}
