import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *  Main class to be extended for game projects.
 *  Creates the window and {@link Group} objects,
 *    and manages the life cycle of the game (initialization and game loop).
 */
public abstract class Game
        extends Application
        implements Screen
{
    /**
     * area where game graphics are displayed
     */
    public Canvas canvas;

    /**
     * object with methods to draw game entities on canvas
     */
    public GraphicsContext context;

    /**
     * The root collection for all {@link Entity} objects in this game.
     */
    public Group group;

    /**
     * The window containing the game.
     */
    public Stage stage;

    public Input input;

    /**
     *  Initializes the window and game objects,
     *  and manages the life cycle of the game (initialization and game loop).
     */
    public void start(Stage mainStage)
    {
        mainStage.setTitle("Game");
        mainStage.setResizable(false);

        Pane root = new Pane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.sizeToScene();

        canvas = new Canvas(512, 512);
        context = canvas.getGraphicsContext2D();
        root.getChildren().add( canvas );

        group = new Group();

        input = new Input(mainScene);

        // to clarify class containing update method
        Game self = this;

        AnimationTimer gameloop = new AnimationTimer()
        {
            public void handle(long nanotime)
            {
                // update user input
                input.update();

                // update game state
                self.update();

                // clear the canvas
                self.context.setFill(Color.GRAY);
                self.context.fillRect( 0,0,
                        self.canvas.getWidth(),
                        self.canvas.getHeight() );

                // render game objects
                self.group.draw( self.context );
            }
        };

        mainStage.show();

        // reference required for set methods
        stage = mainStage;

        initialize();
        gameloop.start();
    }

    /**
     * Set the text that appears in the window title bar
     * @param title window title
     */
    public void setTitle(String title)
    {
        stage.setTitle(title);
    }

    /**
     * set the size of the window/canvas that displays game graphics
     * @param width window/canvas width
     * @param height window/canvas height
     */
    public void setWindowSize(int width, int height)
    {
        stage.setWidth(width);
        stage.setHeight(height);
        canvas.setWidth(width);
        canvas.setHeight(height);
    }
}
