import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestJavaFX extends Application
{
    public double x;
    public double y;

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

        // add a canvas to the window
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext context = canvas.getGraphicsContext2D();

        Image ball = new Image("images/basketball.png",
                100, 100, true, true);

        x = 10;
        y = 10;

        // must add canvas to scene graph
        root.getChildren().add( canvas );

        // the code in the handle method runs
        //   approximately 60 times / second
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long nanoTime)
            {
                // clear the canvas
                context.setFill( Color.ALICEBLUE );
                context.fillRect(0,0, 800,600);

                // move the image across the canvas
                x += 2;
                y += 1;
                context.drawImage( ball, x, y );
            }
        };

        // don't forget to start the timer!
        timer.start();

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
