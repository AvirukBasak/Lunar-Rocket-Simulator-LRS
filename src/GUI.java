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

    public GUI(String title,int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;

        // set LookAndFeel, using default look and feel, gnome and windows supported
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");     // LINUX UI
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex){
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");     // Windows UI
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {}
        }
        createUI();
    }

    // Creates the UI with window and components
    public void createUI()
    {
        // UI object definations.
        cnvsSpace = new Canvas();

        // JFrame frame properties.
        setTitle("Lunar Rocket Sim LRS");
        BufferedImage AppIcon = new SpriteSheet(ImageLoader.loadImage("res/AppIcon.png")).Crop(0, 0, 48, 48);
        setIconImage(AppIcon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width,height);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Canvas cnvsSpace properties.
        cnvsSpace.setBounds(0, 0, width, height);
        cnvsSpace.setMinimumSize(new Dimension(width-256, height-128));
        cnvsSpace.setBackground(Color.black);

        // JFrame frame adding components.
        add(cnvsSpace);
        setVisible(true);
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
