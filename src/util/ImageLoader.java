package util;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader
{
    //Made static so that it is callable easily as instance of class.
    public static BufferedImage loadImage(String path)
    {
        /* Returns an image after reading
         * it from input directory, here
         * directory is given by variable String path.
         * NOTE that ImageIO.read() method always throws
         * IOException.
         */
        BufferedImage img = null;
        try {
            InputStream ips = ImageLoader.class.getClassLoader().getResourceAsStream(path);
            if (ips == null) throw new NullPointerException("input stream is null");
            img = ImageIO.read(ips);
            if (img == null) throw new NullPointerException("failed to load image");
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return img;
    }
}
