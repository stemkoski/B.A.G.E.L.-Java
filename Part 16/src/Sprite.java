
import javafx.scene.canvas.GraphicsContext;

public class Sprite extends Entity
{
    /**
     * sprite location in game world
     */
    public Vector position;

    /**
     * image displayed when rendering this sprite
     */
    public Texture texture;

    /**
     * shape used for collision
     */
    public Rectangle boundary;

    /**
     * width of sprite
     */
    public double width;

    /**
     * height of sprite
     */
    public double height;

    /**
     * determines if sprite will be visible
     */
    public boolean visible;

    public void draw(GraphicsContext context)
    {

    }

}

