
import javafx.scene.canvas.GraphicsContext;

/**
 * Base class for all objects or collections of objects
 * that can be rendered to the screen.
 */
public abstract class Entity
{
    /**
     * Render this Entity to a canvas. 
     * @param context GraphicsContext object that handles drawing to the canvas
     */
    public abstract void draw(GraphicsContext context);

}