import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader
{
    //Made static so that it is callable easily as instance of class.
    public static BufferedImage loadImage(String path)
    {
        /*Returns an image after reading
         *it from input directory, here
         *directory is given by variable String path.
         *NOTE that ImageIO.read() method always throws
         *IOException.
         */
        try {
            return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
