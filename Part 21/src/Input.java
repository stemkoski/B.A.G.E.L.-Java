import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;

/**
 * A structure for storing and updating keyboard state:
 *  which keys are currently pressed or just pressed/released.
 */
public class Input
{
    public ArrayList<String> justPressedQueue;
    public ArrayList<String> justReleasedQueue;
    public ArrayList<String> justPressedList;
    public ArrayList<String> stillPressedList;
    public ArrayList<String> justReleasedList;

    /**
     * Initialize object and activate event listeners.
     * @param listeningScene the window Scene that has focus during the game
     */
    public Input(Scene listeningScene)
    {
        justPressedQueue  = new ArrayList<String>();
        justReleasedQueue = new ArrayList<String>();
        justPressedList   = new ArrayList<String>();
        stillPressedList  = new ArrayList<String>();
        justReleasedList  = new ArrayList<String>();

        // Example Strings: UP, LEFT, Q, DIGIT1, SPACE, SHIFT
        listeningScene.setOnKeyPressed(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    justPressedQueue.add(keyName);
                }
        );

        listeningScene.setOnKeyReleased(
                (KeyEvent event) ->
                {
                    String keyName = event.getCode().toString();
                    justReleasedQueue.add(keyName);
                }
        );
    }



    /**
     *  Update input state information.
     *  Automatically called by {@link Game} class during the game loop.
     */
    public void update()
    {
        // clear previous discrete event status
        justPressedList.clear();
        justReleasedList.clear();

        // update current event status
        for (String keyName : justPressedQueue)
        {
            // avoid multiple keypress events while holding key
            // avoid duplicate entries in key pressed list
            if ( stillPressedList.contains(keyName) )
            {
                justPressedList.add(keyName);
                stillPressedList.add(keyName);
            }
        }

        for (String keyName : justReleasedQueue)
        {
            stillPressedList.remove(keyName);
            justReleasedList.add(keyName);
        }

        // clear the queues used to store events
        justPressedQueue.clear();
        justReleasedQueue.clear();
    }

    /**
     * Determine if key has been pressed / moved to down position (a discrete action).
     * @param keyName name of corresponding key (examples: "LEFT", "A", "DIGIT1", "SPACE", "SHIFT")
     * @return true if key was just pressed
     */
    public boolean isKeyJustPressed(String keyName)
    {  return justPressedList.contains(keyName);  }

    /**
     * Determine if key is currently being pressed / held down (a continuous action).
     * @param keyName name of corresponding key (examples: "LEFT", "A", "DIGIT1", "SPACE", "SHIFT")
     * @return true if key is currently pressed
     */
    public boolean isKeyPressed(String keyName)
    {  return stillPressedList.contains(keyName);  }

    /**
     * Determine if key has been released / returned to up position (a discrete action).
     * @param keyName name of corresponding key (examples: "LEFT", "A", "DIGIT1", "SPACE", "SHIFT")
     * @return true if key was just released
     */
    public boolean isKeyJustReleased(String keyName)
    {  return justReleasedList.contains(keyName);  }
}

