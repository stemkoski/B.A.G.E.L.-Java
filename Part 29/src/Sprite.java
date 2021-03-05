
import javafx.scene.canvas.GraphicsContext;

public class Sprite extends Entity
{
    /**
     * sprite location in game world
     */
    public Vector position;

    /**
     * angle of rotation (in degrees) of the texture
     */
    public double angle;

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
        angle = 0;
        texture = new Texture();
        boundary = new Rectangle();
        visible = true;
    }

    /**
     * Set the coordinates of the center of this sprite.
     * @param x x-coordinate of center of sprite
     * @param y y-coordinate of center of sprite
     */
    public void setPosition(double x, double y)
    {
        position.setValues(x, y);
        boundary.setPosition(x, y);
    }

    /**
     * Move this sprite by the specified amounts.
     * @param dx amount to move sprite along x direction
     * @param dy amount to move sprite along y direction
     */
    public void moveBy(double dx, double dy)
    {
        position.addValues(dx, dy);
    }

    public void setAngle(double a)
    {
        angle = a;
    }

    /**
     * Rotate sprite by the specified angle.
     * @param da the angle (in degrees) to rotate this sprite
     */
    public void rotateBy(double da)
    {
        angle += da;
    }

    /**
     * Move sprite by the specified distance at the specified angle.
     * @param dist the distance to move this sprite
     * @param a the angle (in degrees) along which to move this sprite
     */
    public void moveAtAngle(double dist, double a)
    {
        double A = Math.toRadians(a);
        double dx = dist * Math.cos(A);
        double dy = dist * Math.sin(A);
        moveBy( dx, dy );
    }

    /**
     * Move sprite forward by the specified distance at current angle.
     * @param dist the distance to move this sprite
     */
    public void moveForward(double dist)
    {
        moveAtAngle(dist, angle);
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

    /**
     * Get boundary shape for this sprite, adjusted according to current position.
     * Angle of rotation has no effect on the boundary.
     * @return boundary shape for this sprite
     */
    public Rectangle getBoundary()
    {
        boundary.setPosition( position.x, position.y );
        return boundary;
    }

    /**
     * Check if this sprite is overlapping another sprite.
     * @param other sprite to check for overlap with
     * @return true if this sprite overlaps other sprite
     */
    public boolean overlaps(Sprite other)
    {
        return this.getBoundary().overlaps( other.getBoundary() );
    }

    /**
     * Prevent this sprite from overlapping another sprite
     * by adjusting the position of this sprite.
     * @param other sprite to prevent overlap with
     */
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

        double A = Math.toRadians(angle);
        double cosA = Math.cos(A);
        double sinA = Math.sin(A);

        // apply rotation and translation to image
        context.setTransform( cosA, sinA, -sinA, cosA,
                position.x, position.y );

        // define source rectangle region of image
        // and destination rectangle region of canvas
        context.drawImage( texture.image,
                texture.region.left, texture.region.top,
                texture.region.width, texture.region.height,
                -this.width/2, -this.height/2,
                this.width, this.height );
    }

}

