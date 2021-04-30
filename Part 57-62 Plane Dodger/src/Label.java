import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Label extends Entity
{
    /**
     * Name of the font.
     */
    String fontName;

    /**
     * Size of the font.
     */
    public int fontSize;

    /**
     * Automatically set by constructor.
     */
    Font font;

    /**
     * color used to draw font
     */
    public Color fontColor;

    /**
     * text to display in label
     */
    public String text;

    /**
     * position of anchor of label; see {@link #setPosition(double, double)}
     */
    public Vector position;

    /**
     * text alignment ("LEFT", "CENTER", "RIGHT") with respect to anchor point (x,y)
     */
    public String alignment;

    /**
     * determines if font border will be drawn
     */
    public boolean drawBorder;

    /**
     * size of font border
     */
    public int borderSize;

    /**
     * color used to draw font border
     */
    public Color borderColor;

    /**
     * determines if label will be visible
     */
    public boolean visible;

    public Label(String fontName, int fontSize)
    {
        this.fontName = fontName;
        this.fontSize = fontSize;
        this.font = new Font(fontName, fontSize);
        this.fontColor = Color.BLACK;
        this.text = "...";
        this.position = new Vector();
        this.alignment = "LEFT";
        this.drawBorder = false;
        this.borderColor = Color.BLACK;
        this.borderSize = 1;
        this.visible = true;
    }

    /**
     * Set the coordinates of the anchor position of this label;
     *  this may be to the left, center, or right of the text
     *  according to the value of {@link #alignment}.
     * @param x x-coordinate of anchor of label
     * @param y y-coordinate of anchor of label
     */
    public void setPosition(double x, double y)
    {
        position.setValues(x,y);
    }

    public void setText(String t)
    {
        text = t;
    }

    public void setBorder(int size, Color color)
    {
        drawBorder = true;
        borderSize = size;
        borderColor = color;
    }

    public void draw(GraphicsContext context)
    {
        if (!visible)
            return;

        context.setFont(font);
        context.setFill(fontColor);

        if (alignment.equals("LEFT"))
            context.setTextAlign(TextAlignment.LEFT);
        else if (alignment.equals("CENTER"))
            context.setTextAlign(TextAlignment.CENTER);
        else if (alignment.equals("RIGHT"))
            context.setTextAlign(TextAlignment.RIGHT);

        context.setTransform(1,0, 0,1, 0,0);
        context.setGlobalAlpha(1);
        context.fillText(text, position.x, position.y);

        if (drawBorder)
        {
            context.setStroke(borderColor);
            context.setLineWidth(borderSize);
            context.strokeText(text, position.x, position.y);
        }
    }

    public void update(double dt)
    {
        // empty method
    }
}
