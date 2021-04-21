import javafx.scene.image.Image;

import java.util.ArrayList;

public class Animation
{
    public ArrayList<Texture> textureList;

    public double frameDuration;

    public boolean loop;

    public double elapsedTime;

    public boolean paused;

    public Animation()
    {
        textureList = new ArrayList<Texture>();
        frameDuration = 1;
        loop = false;
        elapsedTime = 0;
        paused = false;
    }

    public Animation(String imageFileName,
                     int rows, int columns,
                     double frameDuration,
                     boolean loop)
    {
        textureList = new ArrayList<Texture>();

        Image image = new Image(imageFileName);
        double frameWidth = image.getWidth()/columns;
        double frameHeight = image.getHeight()/rows;
        for (int y = 0; y < rows; y++)
        {
            for (int x = 0; x < columns; x++)
            {
                Texture tex = new Texture();
                tex.image = image;
                int frameX = x * (int)frameWidth;
                int frameY = y * (int)frameHeight;
                tex.region = new Rectangle(
                        frameX, frameY,
                        frameWidth, frameHeight);
                textureList.add(tex);
            }
        }

        this.frameDuration = frameDuration;
        this.loop = loop;
        this.elapsedTime = 0;
        this.paused = false;
    }

    public Texture getCurrentTexture()
    {
        int textureIndex =
                (int)Math.floor(elapsedTime / frameDuration);

        // avoid out-of-bounds errors
        if (textureIndex >= textureList.size())
            textureIndex = textureList.size() - 1;

        return textureList.get(textureIndex);
    }

    public void update(double dt)
    {
        if (paused)
            return;

        elapsedTime += dt;

        if ( loop && isFinished() )
            elapsedTime = 0;
    }

    public boolean isFinished()
    {
        return (elapsedTime > frameDuration * textureList.size() );
    }

    public Animation clone()
    {
        Animation a = new Animation();
        a.textureList = this.textureList;
        a.frameDuration = this.frameDuration;
        a.loop = this.loop;
        return a;
    }
}
