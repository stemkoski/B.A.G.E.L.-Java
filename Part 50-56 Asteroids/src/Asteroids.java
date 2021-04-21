public class Asteroids extends Game
{
    public Sprite spaceship;
    public Group rockGroup;
    public Texture rockTex;
    public Group laserGroup;
    public Texture laserTex;
    public Animation explosionAnim;
    public Sprite shields;

    public void initialize()
    {
        setTitle("Asteroids");
        setWindowSize(800,600);

        Sprite background = new Sprite();
        Texture bgTex = new Texture("images/space.png");
        background.setTexture( bgTex );
        background.setPosition(400,300);
        group.add( background );

        spaceship = new Sprite();
        Texture ssTex = new Texture("images/spaceship.png");
        spaceship.setTexture(ssTex);
        spaceship.setPosition(400,300);
        spaceship.setPhysics( new Physics(200, 200, 20) );
        spaceship.addAction( Action.wrapToScreen(800,600) );
        group.add( spaceship );

        rockGroup = new Group();
        group.add( rockGroup );
        int rockCount = 8;
        rockTex = new Texture("images/asteroid.png");
        for (int i = 0; i < rockCount; i++)
        {
            Sprite rock = new Sprite();
            rock.setTexture( rockTex );
            rock.setSize(100,100);

            double angle = 2 * Math.PI * Math.random();
            double x = spaceship.position.x
                       + 250 * Math.cos(angle);
            double y = spaceship.position.y
                       + 250 * Math.sin(angle);
            rock.setPosition(x,y);

            rock.setPhysics( new Physics(0, 100, 0) );

            double moveSpeed = 30 * Math.random() + 90;
            rock.physics.setSpeed(moveSpeed);
            rock.physics.setMotionAngle(
                    Math.toDegrees(angle) );

            double rotateSpeed = 2 * Math.random() + 1;
            rock.addAction(
                    Action.forever(Action.rotateBy(360, rotateSpeed) )
            );

            rock.addAction( Action.wrapToScreen(800,600) );
            rockGroup.add(rock);
        }

        laserGroup = new Group();
        group.add( laserGroup );
        laserTex = new Texture("images/laser.png");

        explosionAnim = new Animation(
                "images/explosion.png", 6,6, 0.02, false);

        shields = new Sprite();
        Texture shieldTex = new Texture("images/shields.png");
        shields.setTexture( shieldTex );
        shields.setSize(120,120);
        group.add(shields);
    }

    public void update()
    {
        shields.alignToSprite(spaceship);

        if ( input.isKeyPressed("LEFT") )
            spaceship.rotateBy(-3);

        if ( input.isKeyPressed("RIGHT") )
            spaceship.rotateBy(3);

        if ( input.isKeyPressed("UP") )
            spaceship.physics.accelerateAtAngle(spaceship.angle);

        if ( input.isKeyJustPressed("SPACE") )
        {
            Sprite laser = new Sprite();
            laser.setTexture(laserTex);
            laser.alignToSprite(spaceship);
            laser.setPhysics( new Physics(0,400,0) );
            laser.physics.setSpeed(400);
            laser.physics.setMotionAngle(spaceship.angle);
            laser.addAction( Action.wrapToScreen(800,600) );
            laserGroup.add(laser);

            laser.addAction( Action.delayFadeRemove(1, 0.5) );
        }

        for (Entity rockE : rockGroup.getList())
        {
            Sprite rock = (Sprite)rockE;

            if (shields.overlaps(rock) && shields.opacity > 0)
            {
                rock.removeSelf();
                shields.opacity -= 0.25;

                Sprite explosion = new Sprite();
                explosion.setAnimation(
                        explosionAnim.clone() );
                explosion.alignToSprite(rock);
                explosion.addAction( Action.animateThenRemove() );

                group.add( explosion );
            }

            // game over
            if (rock.overlaps(spaceship))
            {
                // spaceship.removeSelf();
                // TODO: game over message appears
            }

            for (Entity laserE : laserGroup.getList())
            {
                Sprite laser = (Sprite)laserE;
                if (rock.overlaps(laser))
                {
                    rockGroup.remove(rock);
                    laserGroup.remove(laser);
                    Sprite explosion = new Sprite();
                    explosion.setAnimation(
                            explosionAnim.clone() );
                    explosion.alignToSprite(rock);

                    explosion.addAction( Action.animateThenRemove() );

                    group.add( explosion );

                    // if rock is large (100x100),
                    //  split into two smaller rocks
                    if (rock.width == 100)
                    {
                        for (int i = 0; i < 2; i++)
                        {
                            Sprite rockSmall = new Sprite();
                            rockSmall.setTexture(rockTex);
                            rockSmall.setSize(50, 50);
                            rockSmall.alignToSprite(rock);
                            rockSmall.addAction(Action.wrapToScreen(800,600));
                            rockSmall.setPhysics(new Physics(0, 300, 0));
                            rockSmall.physics.setSpeed(
                                     2 * rock.physics.getSpeed());
                            rockSmall.physics.setMotionAngle(
                                    rock.physics.getMotionAngle() + 90*Math.random() - 45);
                            rockGroup.add(rockSmall);
                        }
                    }
                }
            }
        }
    }
}
