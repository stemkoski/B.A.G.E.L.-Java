import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestJavaFX extends Application
{
    public void start(Stage mainStage)
    {
        // set text that appears in window title bar
        mainStage.setTitle("My JavaFX Application");

        // a Pane is a layout manager
        Pane root = new Pane();

        // Scene contains content within Stage/Window
        // parameters: layout manager, width, height
        Scene mainScene = new Scene( root, 800, 600 );
        // attach scene to stage
        mainStage.setScene( mainScene );

        // add a text label to the window
        Label message = new Label("Hello, world!");
        message.setLayoutX(400);
        message.setLayoutY(300);
        // must add label to scene graph
        root.getChildren().add( message );

        // make the window visible
        mainStage.show();
    }

    // driver method
    public static void main(String[] args)
    {
        try
        {
            launch(args);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }
}
