import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.*;
import maze.*;
import maze.visualisation.*;
import maze.routing.RouteFinder;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import java.io.FileNotFoundException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.control.ContextMenu;
import javafx.scene.text.Text;
/** Class to handle the visuals of the program
 *  @author Nitu Radu Florin
 *  @version 29th April 2021
*/
public class MazeApplication extends Application {

  private Maze m1;
  private RouteFinder r1;

/** The main method. Launches the application
 *
 * @param args: The parameters used for launching
*/
  public static void main(String[] args){
    launch(args);
  }

/** Handles the visual represenation of the app
 *
 * @param stage: The stage where the elements will pe placed
 * @throws FileNotFoundException For the reads from external files
*/
  public void start(Stage stage) throws FileNotFoundException{




    Button buttonLoad = new Button("Load Map");
    buttonLoad.setLayoutX(10);
    buttonLoad.setLayoutY(10);
    Button buttonRoute = new Button("Load Route");
    buttonRoute.setLayoutX(10);
    buttonRoute.setLayoutY(50);
    Button buttonSave = new Button("Save Route");
    buttonSave.setLayoutX(10);
    buttonSave.setLayoutY(90);
    Button buttonStep = new Button("Step");
    buttonStep.setLayoutX(10);
    buttonStep.setLayoutY(460);
    HBox vboxButtons = new HBox(buttonLoad, buttonRoute, buttonSave);
    vboxButtons.setAlignment(Pos.CENTER);
    vboxButtons.setSpacing(10);
    VBox vboxMaze = new VBox();


    buttonStep.setOnAction(e->{
      boolean finish = r1.step();

      vboxMaze.getChildren().clear();
      for(int i=0 ;i < r1.getMaze().getTiles().size(); i++)
      {
        //in hbox there will be the rows of the maze
        HBox hbox = new HBox();

        for(int j=0; j < r1.getMaze().getTiles().get(i).size(); j++)
        {
          hbox.getChildren().addAll(Visuals.getVisualRepresentation(r1.getMaze().getTiles().get(i).get(j),r1));
        }
        vboxMaze.getChildren().addAll(hbox);
      }
      VBox allignment = new VBox(vboxButtons, vboxMaze, buttonStep);
      Group root = new Group(allignment);
      if(finish)
      {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Maze solved!");
        dialog.setTitle("Maze solved");
        dialog.show();
      }
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Maze visuals");
      stage.show();
});

      buttonSave.setOnAction(e1->{
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter the name you want the file to be saved as (without extension)");
        dialog.setTitle("File Naming");
        Optional<String> response = dialog.showAndWait();
        r1.save(response.get() + ".obj");
      });

      buttonRoute.setOnAction(e2->{
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter the name you want the file to load (without extension)");
        dialog.setTitle("File Loading");
        Optional<String> response = dialog.showAndWait();
        r1 = RouteFinder.load(response.get() + ".obj");

        vboxMaze.getChildren().clear();
        for(int i=0 ;i < r1.getMaze().getTiles().size(); i++)
        {
          //in hbox there will be the rows of the maze
          HBox hbox = new HBox();

          for(int j=0; j < r1.getMaze().getTiles().get(i).size(); j++)
          {
            hbox.getChildren().addAll(Visuals.getVisualRepresentation(r1.getMaze().getTiles().get(i).get(j),r1));
          }
          vboxMaze.getChildren().addAll(hbox);
        }
        VBox allignment = new VBox(vboxButtons, vboxMaze, buttonStep);
        Group root = new Group(allignment);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Maze visuals");
        stage.show();

      });
      buttonLoad.setOnAction(e3->{
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter the path to the map");
        dialog.setTitle("Map Loading");
        Optional<String> response = dialog.showAndWait();
        vboxMaze.getChildren().clear();
        try{
        m1 = Maze.fromTxt("./resources/mazes/"+response.get()+".txt");
        System.out.println(m1.toString());
        r1 = new RouteFinder(m1);
        }catch(Exception e){
          e.printStackTrace();
        }
        for(int i=0 ;i < r1.getMaze().getTiles().size(); i++)
        {
          //in hbox there will be the rows of the maze
          HBox hbox = new HBox();

          for(int j=0; j < r1.getMaze().getTiles().get(i).size(); j++)
          {
            hbox.getChildren().addAll(Visuals.getVisualRepresentation(r1.getMaze().getTiles().get(i).get(j),r1));
          }
          vboxMaze.getChildren().addAll(hbox);
        }

        VBox allignment = new VBox(vboxButtons, vboxMaze, buttonStep);
        Group root = new Group(allignment);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Maze visuals");
        stage.show();

      });






if(m1!=null)
  {  for(int i=0 ;i < r1.getMaze().getTiles().size(); i++)
    {
      //in hbox there will be the rows of the maze
      HBox hbox = new HBox();

      for(int j=0; j < r1.getMaze().getTiles().get(i).size(); j++)
      {
        hbox.getChildren().addAll(Visuals.getVisualRepresentation(r1.getMaze().getTiles().get(i).get(j),r1));
      }
      vboxMaze.getChildren().addAll(hbox);


    }
}

    VBox allignment = new VBox(vboxButtons, vboxMaze, buttonStep);


    Group root = new Group(allignment);
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.setTitle("Maze visuals");
    stage.show();
  }

}
