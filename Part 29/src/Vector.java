/**
 * A two-dimensional vector (x,y).
 */
public class Vector implements Comparable<Vector>
{
    /**
     *  x-coordinate of the vector
     */
    public double x;

    /**
     *  y-coordinate of the vector
     */
    public double y;

    /**
     * Initializes vector coordinates to (0,0).
     */
    public Vector()
    {
        setValues(0,0);
    }

    /**
     * Initializes vector coordinates to (x,y).
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Vector(double x, double y)
    {
        setValues(x,y);
    }

    /**
     * Set the values of the x- and y- coordinates.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void setValues(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Add the values of the coordinates of other vector
     * to the coordinates of this vector.
     * @param other vector to be added to this vector
     */
    public void addVector(Vector other)
    {
        this.x += other.x;
        this.y += other.y;
    }

    /**
     * Add values to the coordinates of this vector.
     * @param dx value to add to the x-coordinate
     * @param dy value to add to the y-coordinate
     */
    public void addValues(double dx, double dy)
    {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Multiple the coordinates of this vector by
     * a given value.
     * @param scalar the value to multiply the coordinates of this vector by
     */
    public void multiply(double scalar)
    {
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * Calculate the length of this vector
     * @return the length of this vector
     */
    public double getLength()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Calculate the angle (in degrees) between this vector
     * and the x-axis (the vector (1,0)). If this vector is
     * the zero vector, returns 0. Return values are in the
     * range from -180 to +180.
     * @return the angle between this vector and the x-axis
     */
    public double getAngle()
    {
        // range: -180 to 180
        if (this.getLength() == 0)
            return 0;
        else
            return Math.toDegrees( Math.atan2(this.y, this.x) );
    }

    /**
     * Changes the length of this vector to length while
     * preserving the angle of this vector.
     * @param length the new length of this vector
     */
    public void setLength(double length)
    {
        double angleDeg = this.getAngle();
        double angleRad = Math.toRadians( angleDeg );
        this.x = length * Math.cos(angleRad);
        this.y = length * Math.sin(angleRad);
    }

    /**
     * Changes the angle of this vector to angleDegrees
     * while preserving the length of this vector.
     * @param angleDeg the new angle (in degrees) between this vector and the x-axis
     */
    public void setAngle(double angleDeg)
    {
        double length = this.getLength();
        double angleRad = Math.toRadians( angleDeg );
        this.x = length * Math.cos(angleRad);
        this.y = length * Math.sin(angleRad);
    }

    /**
     * Convert this object to a String.
     */
    public String toString()
    {
        return "< " + x + " , " + y + " >";
    }

    public int compareTo(Vector other)
    {
        if (this.getLength() < other.getLength())
            return -1;
        else if (this.getLength() > other.getLength())
            return 1;
        else
            return 0;
    }
}