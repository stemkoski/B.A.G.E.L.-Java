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
        ball.position.addValues(2, 1);
    }
}
