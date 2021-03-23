/**
 *  Used to simulate change in position of an object based on velocity and acceleration;
 *  useful in games with a top-down view perspective.
 */
public class Physics
{
    /**
     * Position of object.
     */
    public Vector position;

    /**
     * Velocity (rate of change of position) of object.
     */
    public Vector velocity;

    /**
     * Acceleration (rate of chance of velocity) of object.
     */
    public Vector acceleration;

    /**
     * Constant amount of acceleration, used by {@link #accelerateAtAngle(double)}.
     */
    public double accelerationValue;

    /**
     * Maximum speed possible for object.
     */
    public double maximumSpeed;

    /**
     * Rate of speed reduction to apply when object is not accelerating.
     */
    public double decelerationValue;

    /**
     * Initialize values used by physics simulation.
     * For objects traveling at constant speed,
     * set acceleration and deceleration values to 0.
     * @param accValue acceleration value
     * @param maxSpeed maximum speed
     * @param decValue deceleration value
     */
    public Physics(double accValue,
                   double maxSpeed,
                   double decValue)
    {
        position = new Vector();
        velocity = new Vector();
        acceleration = new Vector();
        accelerationValue = accValue;
        maximumSpeed = maxSpeed;
        decelerationValue = decValue;
    }

    /**
     * Calculate speed of object.
     * @return speed of object
     */
    public double getSpeed()
    {
        return velocity.getLength();
    }

    /**
     * Set speed of object.
     * If acceleration and deceleration values are 0, the speed will remain constant.
     * @param speed speed of object
     */
    public void setSpeed(double speed)
    {
        velocity.setLength(speed);
    }

    /**
     * Calculate the angle of motion (in degrees)
     * as measured from the x-axis (the vector (1,0)).
     * If the speed is 0, this method returns 0.
     * Return values are in the range from -180 to +180.
     * @return angle of motion of object
     */
    public double getMotionAngle()
    {
        return velocity.getAngle();
    }

    /**
     * Set the angle of motion of this object.
     * If the speed is 0, this method has no effect.
     * @param angleDeg angle of motion of object
     */
    public void setMotionAngle(double angleDeg)
    {
        velocity.setAngle(angleDeg);
    }

    public void accelerateBy(double amount, double angle)
    {
        Vector a = new Vector();
        a.setLength(amount);
        a.setAngle(angle);
        acceleration.addVector(a);
    }

    /**
     * Accelerate this object in the direction angleDegrees
     *  by the amount specified by {@link #accelerationValue}.
     * @param angleDeg direction of acceleration
     */
    public void accelerateAtAngle(double angleDeg)
    {
        accelerateBy(accelerationValue, angleDeg);
    }

    /**
     * Update the position of this object
     *  according to velocity and acceleration.
     *  Deceleration is applied if no acceleration is present.
     * @param dt elapsed time (seconds) since previous iteration of game loop
     *   (typically approximately 1/60 second)
     */
    public void update(double dt)
    {
        // apply acceleration
        velocity.addValues(
                acceleration.x * dt,
                acceleration.y * dt);

        double speed = getSpeed();

        // decrease speed when not accelerating
        if (acceleration.getLength() < 0.001)
            speed -= decelerationValue * dt;

        // keep speed between set bounds
        if (speed < 0)
            speed = 0;
        if (speed > maximumSpeed)
            speed = maximumSpeed;

        setSpeed(speed);

        position.addValues(
                velocity.x * dt,
                velocity.y * dt);

        acceleration.setValues(0, 0);
    }
}

