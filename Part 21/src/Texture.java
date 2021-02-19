import javafx.scene.image.Image;

import java.io.File;

/**
 * Image data used for drawing a Sprite.
 * Texture objects are typically created using the {@link #load(String)} method.
 * Multiple instances of a Sprite may share a single Texture reference.
 *
 */
public class Texture
{
    /**
     *  The image to be drawn.
     */
    public Image image;

    /**
     *  A rectangular sub-area of the image to be drawn.
     */
    public Rectangle region;

    /**
     *  Create an empty texture.
     */
    public Texture()
    {

    }

    /**
     * Create a Texture from the image file with the given file name.
     * Sets {@link #region} to the original image dimensions.
     * @param imageFileName name of the image file
     * @return A Texture object that displays the image file with the given file name.
     */
    public static Texture load(String imageFileName)
    {
        Texture tex = new Texture();
        String fileName = new File(imageFileName).toURI().toString();
        tex.image = new Image( fileName );
        tex.region = new Rectangle();
        double width = tex.image.getWidth();
        double height = tex.image.getHeight();
        tex.region.setValues(0,0, width,height);
        return tex;
    }
}
