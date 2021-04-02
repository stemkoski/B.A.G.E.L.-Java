public class TestGame extends Game
{
    Sprite ball;

    public void initialize()
    {
        setTitle("Test Game");
        setWindowSize(800, 600);
        System.out.println("Hello, world!");

        ball = new Sprite();
        ball.setPosition(10, 10);
        ball.setTexture( Texture.load("images/basketball.png") );
        ball.setSize(50, 50);

        group.add(ball);
    }

    public void update()
    {
        if ( input.isKeyPressed("RIGHT") )
            ball.position.addValues(2, 0);

        if ( input.isKeyPressed("LEFT") )
            ball.position.addValues(-2, 0);

        if ( input.isKeyPressed("UP") )
            ball.position.addValues(0, -2);

        if ( input.isKeyPressed("DOWN") )
            ball.position.addValues(0, 2);

        if ( input.isKeyJustPressed("Z") )
            ball.position.setValues(0, 0);
    }
}
