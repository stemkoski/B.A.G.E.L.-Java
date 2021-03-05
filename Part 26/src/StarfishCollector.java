public class StarfishCollector extends Game
{
    Sprite water;
    Group starfishGroup;
    Sprite turtle;
    Sprite winMessage;

    public void initialize()
    {
        setTitle("Starfish Collector");
        setWindowSize(800, 600);

        water = new Sprite();
        water.setTexture( new Texture("images/water.png") );
        group.add( water );

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
            starfishGroup.add( starfish );
        }
        group.add(starfishGroup);

        turtle = new Sprite();
        turtle.setPosition(50, 50);
        turtle.setTexture( new Texture("images/turtle.png") );
        group.add(turtle);

        winMessage = new Sprite();
        winMessage.setPosition(250, 200);
        winMessage.setTexture( new Texture("images/youWin.png") );
        winMessage.visible = false;
        group.add(winMessage);

    }

    public void update()
    {
        if (winMessage.visible)
            return;

        if (input.isKeyPressed("RIGHT"))
            turtle.position.addValues(2, 0);
        if (input.isKeyPressed("LEFT"))
            turtle.position.addValues(-2, 0);
        if (input.isKeyPressed("UP"))
            turtle.position.addValues(0, -2);
        if (input.isKeyPressed("DOWN"))
            turtle.position.addValues(0, 2);

        for ( Entity entity : starfishGroup.getList() )
        {
            Sprite starfish = (Sprite)entity;
            if ( turtle.overlaps(starfish) )
                starfishGroup.remove(starfish);
        }

        if (starfishGroup.size() == 0)
            winMessage.visible = true;

        turtle.boundToScreen(800, 600);
    }
}
