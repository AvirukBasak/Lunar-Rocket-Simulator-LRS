import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

final class GUI extends JFrame
{
    // Variable declarations.
    private final String title;
    private final int width, height;

    // UI object declarations.
    private Canvas cnvsSpace;

    public GUI(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;

        // Set LookAndFeel, using default look and feel, gnome and windows supported
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");     // Linux UI
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");     // Windows UI
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex2) {}
        }
    }

    // Creates the UI with window and components
    public GUI create()
    {
        // UI object definations.
        cnvsSpace = new Canvas();

        // App icon
        final BufferedImage appIcon = new SpriteSheet(ImageLoader.loadImage("res/AppIcon.png")).Crop(0, 0, 48, 48);

        // JFrame frame properties.
        this.setTitle("Lunar Rocket Sim LRS");
        this.setIconImage(appIcon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Canvas cnvsSpace properties.
        cnvsSpace.setBounds(0, 0, width, height);
        cnvsSpace.setMinimumSize(new Dimension(width-256, height-128));
        cnvsSpace.setBackground(Color.black);

        // JFrame frame adding components.
        this.add(cnvsSpace);
        this.setVisible(true);
        return this;
    }

    public void close() {
        this.dispose();
        System.exit(0);
    }

    // Getter for Canvas cnvsSpace.
    public Canvas getCanvas()
    {
        return cnvsSpace;
    }

    public int getWidth()
    {
        return width;
    }

    public int geHeight()
    {
        return height;
    }
}
