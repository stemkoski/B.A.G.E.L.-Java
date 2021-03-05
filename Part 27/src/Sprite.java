
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

    public Sprite()
    {
        position = new Vector();
        texture = new Texture();
        boundary = new Rectangle();
        visible = true;
    }

    /**
     * set the sprite position in the game world
     * @param x x-coordinate of position
     * @param y y-coordinate of position
     */
    public void setPosition(double x, double y)
    {
        position.setValues(x, y);
        boundary.setPosition(x, y);
    }

    /**
     * set the texture data used when drawing this sprite;
     * also sets width and height of sprite
     * @param tex texture data
     */
    public void setTexture(Texture tex)
    {
        texture = tex;
        width = texture.region.width;
        height = texture.region.height;
        boundary.setSize(width, height);
    }

    /**
     * set the width and height of this sprite;
     * used for drawing texture and collision rectangle
     * @param width sprite width
     * @param height sprite height
     */
    public void setSize(int width, int height)
    {
        this.width = width;
        this.height = height;
        boundary.setSize(width, height);
    }

    public Rectangle getBoundary()
    {
        boundary.setPosition( position.x, position.y );
        return boundary;
    }

    public boolean overlaps(Sprite other)
    {
        return this.getBoundary().overlaps( other.getBoundary() );
    }

    public void preventOverlap(Sprite other)
    {
        if (this.overlaps(other))
        {
            Vector mtv = this.getBoundary()
                    .getMinimumTranslationVector( other.getBoundary() );
            this.position.addVector(mtv);
        }
    }

    public void boundToScreen(int screenWidth, int screenHeight)
    {
        if (position.x < 0)
            position.x = 0;
        if (position.y < 0)
            position.y = 0;
        if (position.x + width > screenWidth)
            position.x = screenWidth - width;
        if (position.y + height > screenHeight)
            position.y = screenHeight - height;
    }
    /**
     * draw this sprite on the canvas
     * @param context GraphicsContext object that handles drawing to the canvas
     */
    public void draw(GraphicsContext context)
    {
        // if sprite is not visible, exit method
        if (!this.visible)
            return;

        // apply rotation and translation to image
        context.setTransform( 1,0, 0,1,
                position.x, position.y );

        // define source rectangle region of image
        // and destination rectangle region of canvas
        context.drawImage( texture.image,
                texture.region.left, texture.region.top,
                texture.region.width, texture.region.height,
                0, 0,
                this.width, this.height );
    }

}

