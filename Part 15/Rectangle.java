/**
 *  A rectangle shape, defined by its position and size,
 *  particularly useful in collision detection.
 */

public class Rectangle
{
    /**
     * x-coordinate of left edge of rectangle
     */
    private double left;

    /**
     * y-coordinate of top edge of rectangle
     */
    private double top;

    /**
     * width of rectangle
     */
    private double width;

    /**
     * height of rectangle
     */
    private double height;

    /**
     * x-coordinate of right edge of rectangle
     */
    private double right;

    /**
     * y-coordinate of bottom edge of rectangle
     */
    private double bottom;

    /**
     * Initialize rectangle with all values set to 0.
     */
    public Rectangle()
    {
        setValues(0,0,0,0);
    }

    /**
     * Initialize rectangle data from coordinates of top-left corner and size.
     * @param left x-coordinate of top-left corner (left edge) of rectangle
     * @param top y-coordinate of top-left corner (top edge) of rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public Rectangle(double left, double top, double width, double height)
    {
        setValues(left, top, width, height);
    }

    /**
     * Set rectangle data.
     * Used to update game entities that move and/or change size.
     * @param left x-coordinate of top-left corner (left edge) of rectangle
     * @param top y-coordinate of top-left corner (top edge) of rectangle
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public void setValues(double left, double top, double width, double height)
    {
        this.left   = left;
        this.top    = top;
        this.width  = width;
        this.height = height;
        this.right  = left + width;
        this.bottom = top + height;
    }

    /**
     * Update rectangle data.
     * Used for game entities that move.
     * @param left x-coordinate of top-left corner (left edge) of rectangle
     * @param top y-coordinate of top-left corner (top edge) of rectangle
     */
    public void setPosition(double left, double top)
    {
        setValues(left, top, this.width, this.height);
    }

    /**
     * Determine if this rectangle overlaps with other rectangle.
     * @param other rectangle to check for overlap
     * @return true if this rectangle overlaps with other rectangle
     */
    public boolean overlaps(Rectangle other)
    {
        boolean noOverlap = (other.right <= this.left)
                || (this.right <= other.left)
                || (other.bottom <= this.top)
                || (this.bottom <= other.top);
        return !noOverlap;
    }

}