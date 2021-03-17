public interface Screen
{
    /**
     * Initialize game objects used in this particular screen.
     */
    public void initialize();

    /**
     * Update game objects used in this particular screen.
     * Runs 60 times per second (when possible).
     */
    public void update();
}
