public class StarfishCollector extends Game
{
    Sprite water;
    Group starfishGroup;
    Sprite turtle;
    Sprite winMessage;
    Group rockGroup;
    Sprite shark;

    public void initialize()
    {
        setTitle("Starfish Collector");
        setWindowSize(800, 600);

        water = new Sprite();
        water.setTexture( new Texture("images/water.png") );
        water.setPosition(400,300);
        group.add( water );

        rockGroup = new Group();
        Texture rockTexture = new Texture("images/rock.png");
        int rockCount = 3;
        for (int i = 0; i < rockCount; i++)
        {
            Sprite rock = new Sprite();
            double x = Math.random() * 600 + 100;
            double y = Math.random() * 400 + 100;
            rock.setPosition(x, y);
            rock.setTexture(rockTexture);
            rockGroup.add( rock );
        }
        group.add( rockGroup );

        shark = new Sprite();
        shark.setPosition(400,300);
        shark.setTexture( new Texture("images/shark.png") );
        group.add( shark );

        starfishGroup = new Group();
        Texture starfishTexture = new Texture("images/starfish.png");
        int starfishCount = 20;
        for (int i = 0; i < starfishCount; i++)
        {
            Sprite starfish = new Sprite();
            double x = Math.random() * 600 + 100;
            double y = Math.random() * 400 + 100;
            starfish.setPosition(x, y);
            starfish.setTexture(starfishTexture);

            boolean rockOverlap;
            do
            {
                rockOverlap = false;
                x = Math.random() * 600 + 100;
                y = Math.random() * 400 + 100;
                starfish.setPosition(x, y);
                for (Entity entity : rockGroup.getList())
                {
                    Sprite rock = (Sprite)entity;
                    if (rock.overlaps(starfish))
                        rockOverlap = true;
                }
            } while( rockOverlap );

            starfishGroup.add( starfish );
        }
        group.add(starfishGroup);

        turtle = new Sprite();
        turtle.setPosition(50, 50);
        turtle.setTexture( new Texture("images/turtle.png") );
        group.add(turtle);

        winMessage = new Sprite();
        winMessage.setPosition(400, 300);
        winMessage.setTexture( new Texture("images/youWin.png") );
        winMessage.visible = false;
        group.add(winMessage);


    }

    public void update()
    {
        if (winMessage.visible)
            return;

        if (input.isKeyPressed("RIGHT"))
        {
            turtle.moveBy(2, 0);
            turtle.setAngle(0);
        }
        if (input.isKeyPressed("LEFT"))
        {
            turtle.moveBy(-2, 0);
            turtle.setAngle(180);
        }
        if (input.isKeyPressed("UP"))
        {
            turtle.moveBy(0, -2);
            turtle.setAngle(270);
        }
        if (input.isKeyPressed("DOWN"))
        {
            turtle.moveBy(0, 2);
            turtle.setAngle(90);
        }

        if ( turtle.position.x < shark.position.x )
            shark.mirrored = true;

        if ( turtle.position.x > shark.position.x )
            shark.mirrored = false;


        for ( Entity entity : starfishGroup.getList() )
        {
            Sprite starfish = (Sprite)entity;
            if ( turtle.overlaps(starfish) )
                starfishGroup.remove(starfish);
        }

        for ( Entity entity : rockGroup.getList() )
        {
            Sprite rock = (Sprite)entity;
            turtle.preventOverlap(rock);
        }

        if (starfishGroup.size() == 0)
            winMessage.visible = true;

        turtle.boundToScreen(800, 600);
    }
}
