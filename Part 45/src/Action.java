public class Action
{
    public interface Function
    {
        boolean run(Sprite target, double deltaTime, double totalTime);
    }

    public Function function;
    public double totalTime;

    public Action()
    {
        totalTime = 0;
    }

    public Action(Function f)
    {
        function = f;
        totalTime = 0;
    }

    public boolean apply(Sprite target, double deltaTime)
    {
        totalTime += deltaTime;
        return function.run(target, deltaTime, totalTime);
    }

    public void reset()
    {
        totalTime = 0;
    }

    // static functions to create Action objects

    public static Action moveBy(
            double deltaX, double deltaY, double duration)
    {
        return new Action(
                (Sprite target, double dt, double tt) ->
                {
                    target.moveBy(deltaX / duration * dt,
                            deltaY / duration * dt);
                    return (tt >= duration);
                }
        );
    }

    public static Action rotateBy(double deltaA, double duration)
    {
        return new Action(
                (Sprite target, double dt, double tt) ->
                {
                    target.rotateBy(deltaA / duration * dt);
                    return (tt >= duration);
                }
        );
    }

    public static Action fadeOut(double duration)
    {
        return new Action(
                (Sprite target, double dt, double tt) ->
                {
                    target.opacity -= (1 / duration * dt);
                    if (target.opacity < 0)
                        target.opacity = 0;
                    return (tt >= duration);
                }
        );
    }
}
