import javafx.scene.paint.Color;

public class StarfishCollector extends Game
{
    Sprite water;
    Group starfishGroup;
    Sprite turtle;
    Group rockGroup;
    Sprite shark;
    Sprite fish;
    Label starfishLabel;
    Label winLabel;

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
            starfish.setSize( (int)(Math.random() * 20 + 40), (int)(Math.random() * 20 + 40));

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

            starfish.addAction(
                    Action.repeat(
                        Action.sequence(
                               Action.moveBy(50,0, 1),
                               Action.moveBy(-50,0, 1)
                        ), 3
                    )
            );

            starfishGroup.add( starfish );
        }
        group.add(starfishGroup);

        turtle = new Sprite();
        turtle.setPosition(90, 90);
        turtle.setTexture( new Texture("images/turtle.png") );
        turtle.setPhysics( new Physics(400, 200, 400) );
        turtle.addAction( Action.boundToScreen(800,600) );
        group.add(turtle);

        // optional: add a transparent water layer on top
        //   to create an "underwater" effect
        /*
        Sprite water2 = new Sprite();
        water2.setTexture( new Texture("images/water.png") );
        water2.setPosition(400,300);
        water2.opacity = 0.30;
        group.add( water2 );
        */

        fish = new Sprite();
        fish.setPosition(100, 400);
        Animation fishAnim = new Animation("images/fish.png", 8, 1, 0.1, true);
        fish.setAnimation(fishAnim);
        fish.setPhysics( new Physics(0,200,0) );
        fish.physics.setSpeed(200);
        fish.physics.setMotionAngle(45);
        fish.setAngle(45);
        fish.addAction( Action.wrapToScreen(800, 600) );
        group.add(fish);

        winLabel = new Label("Comic Sans MS Bold", 80);
        winLabel.setText("You Win!");
        winLabel.fontColor = Color.LIGHTGREEN;
        winLabel.setBorder(2, Color.DARKGREEN);
        winLabel.setPosition(400, 300);
        winLabel.alignment = "CENTER";
        winLabel.visible = false;
        group.add(winLabel);

        starfishLabel = new Label("Comic Sans MS Bold", 48);
        String text = "Starfish Left: " + starfishGroup.size();
        starfishLabel.setText( text );
        starfishLabel.setPosition(780, 580);
        starfishLabel.alignment = "RIGHT";
        starfishLabel.fontColor = Color.YELLOW;
        starfishLabel.setBorder(2, Color.BLACK);
        group.add(starfishLabel);
    }

    public void update()
    {
        if (winLabel.visible)
            return;

        if (input.isKeyPressed("RIGHT"))
            turtle.physics.accelerateAtAngle(0);

        if (input.isKeyPressed("LEFT"))
            turtle.physics.accelerateAtAngle(180);

        if (input.isKeyPressed("UP"))
            turtle.physics.accelerateAtAngle(270);

        if (input.isKeyPressed("DOWN"))
            turtle.physics.accelerateAtAngle(90);

        if ( turtle.physics.getSpeed() > 0 )
            turtle.setAngle(turtle.physics.getMotionAngle());



       if ( turtle.position.x < shark.position.x )
            shark.mirrored = true;

        if ( turtle.position.x > shark.position.x )
            shark.mirrored = false;


        for ( Entity entity : starfishGroup.getList() )
        {
            Sprite starfish = (Sprite)entity;

            if ( turtle.overlaps(starfish) )
            {
                starfishGroup.remove(starfish);
                String text = "Starfish Left: " + starfishGroup.size();
                starfishLabel.setText( text );
            }
        }

        for ( Entity entity : rockGroup.getList() )
        {
            Sprite rock = (Sprite)entity;
            turtle.preventOverlap(rock);
        }

        if (starfishGroup.size() == 0)
            winLabel.visible = true;
    }
}
