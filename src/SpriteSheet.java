import java.awt.image.BufferedImage;

public class SpriteSheet
{
    //Global BufferedImage object
    private final BufferedImage bfSheet;

    public SpriteSheet(BufferedImage bfSheet)
    {
        this.bfSheet = bfSheet;
    }

    public BufferedImage Crop(int x, int y, int width, int height)
    {
        /*Method is used to crop and pass real image from
         *file to a BufferedImage object.
         */
        return bfSheet.getSubimage(x, y, width, height);
    }

}
