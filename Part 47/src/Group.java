
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *  A collection of {@link Entity} objects.
 *
 */
public class Group extends Entity
{
    /**
     *  The collection underlying this list.
     */
    private ArrayList<Entity> list;


    /**
     *  Initialize this object.
     */
    public Group()
    {
        this.list = new ArrayList<Entity>();
    }

    /**
     *  Add an {@link Entity} to this collection.
     *  @param e The Entity being added to this collection.
     */
    public void add(Entity e)
    {
        this.list.add(e);
    }

    /**
     *  Remove an {@link Entity} from this collection.
     *  @param e The Entity being removed from this collection.
     */
    public void remove(Entity e)
    {
        this.list.remove(e);
    }

    /**
     *  Retrieve a (shallow) copy of this list.
     *  Especially useful in for loops.
     *  Avoids concurrent modification exceptions
     *  when adding/removing from this collection during iteration.
     * @return a copy of the list underlying this collection
     */
    public ArrayList<Entity> getList()
    {
        return new ArrayList<Entity>(list);
    }

    /**
     * Determine the number of entities in this collection.
     * @return the size of this collection
     */
    public int size()
    {
        return this.list.size();
    }

    /**
     *  Render all Entity objects in this collection to a canvas.
     */
    public void draw(GraphicsContext context)
    {
        for ( Entity e : this.list )
            e.draw(context);
    }

    /**
     *  Update all Entity objects in this collection.
     */
    public void update(double dt)
    {
        for ( Entity e : this.list )
            e.update(dt);
    }

}
