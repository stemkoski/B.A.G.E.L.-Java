import javafx.scene.paint.Color;

public class PlaneDodger extends Game
{
    public Sprite sky;
    public Sprite ground;
    public Sprite player;
    public Group enemyGroup;
    public Texture enemyTex;
    public double enemyTimer;
    public double enemySpeed;
    public Group starGroup;
    public Texture starTex;
    public double starTimer;
    public int score;
    public Label scoreLabel;


    public void initialize()
    {
        setTitle("Plane Dodger");
        setWindowSize(600,800);

        sky = new Sprite();
        Texture skyTex = new Texture("images/sky.png");
        sky.setTexture( skyTex );
        sky.setPosition(300,400);
        sky.setPhysics(new Physics(0,150,0));
        sky.physics.setSpeed(20);
        sky.physics.setMotionAngle(180);
        group.add(sky);

        ground = new Sprite();
        Texture groundTex = new Texture("images/ground.png");
        ground.setTexture(groundTex);
        ground.setPosition(300,760);
        ground.setPhysics(new Physics(0,150,0));
        ground.physics.setSpeed(150);
        ground.physics.setMotionAngle(180);
        group.add(ground);

        player = new Sprite();
        Texture playerTex = new Texture("images/plane-green.png");
        player.setTexture(playerTex);
        player.setPosition(80,80);
        player.setPhysics(new Physics(200,600,0));
        player.addAction( Action.boundToScreen(600,800) );
        group.add(player);

        enemyGroup = new Group();
        group.add(enemyGroup);
        enemyTex = new Texture("images/plane-red.png");
        enemyTimer = 2.0;
        enemySpeed = 200;

        starGroup = new Group();
        group.add(starGroup);
        starTex = new Texture("images/star.png");
        starTimer = 2.0;

        scoreLabel = new Label("Arial Bold", 64);
        scoreLabel.fontColor = Color.WHITE;
        scoreLabel.drawBorder = true;
        scoreLabel.setPosition(300, 80);
        scoreLabel.setText("" + score);
        group.add(scoreLabel);
    }

    public void update()
    {
        if (sky.position.x < 0)
            sky.moveBy(600,0);

        if (ground.position.x < 0)
            ground.moveBy(600,0);

        player.physics.accelerateAtAngle(90);

        if (input.isKeyJustPressed("SPACE"))
        {
            player.physics.setSpeed(200);
            player.physics.setMotionAngle(270);
        }

        enemyTimer -= 1.0/60.0;
        if (enemyTimer < 0)
        {
            Sprite enemy = new Sprite();
            enemy.setTexture(enemyTex);
            double enemyY = Math.random() * 600 + 100;
            enemy.setPosition(700,enemyY);
            enemy.setPhysics(new Physics(0,600,0));
            enemy.physics.setSpeed(enemySpeed);
            enemy.physics.setMotionAngle(180);
            enemyGroup.add(enemy);
            enemyTimer = 2.0;
            enemySpeed += 10;
        }

        starTimer -= 1.0/60.0;
        if (starTimer < 0)
        {
            Sprite star = new Sprite();
            star.setTexture(starTex);
            double starY = Math.random() * 600 + 100;
            star.setPosition(700,starY);
            star.setPhysics(new Physics(0,200,0));
            star.physics.setSpeed(150);
            star.physics.setMotionAngle(180);
            star.setAngle(-15);
            star.addAction(
                    Action.forever(
                            Action.sequence(
                                    Action.rotateBy(30, 0.5),
                                    Action.rotateBy(-30, 0.5)
                            )
                    )
            );
            starGroup.add(star);
            starTimer = 2.0;
        }

        for ( Sprite star : starGroup.getSpriteList() )
        {
            if (player.overlaps(star))
            {
                score += 1;
                scoreLabel.setText( Integer.toString(score) );
                starGroup.remove(star);
            }
        }
    }
}
