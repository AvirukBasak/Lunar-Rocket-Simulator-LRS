import java.awt.image.BufferedImage;

public class AssetsImg
{
    private static boolean init = false;
    /* Global BufferedImage object declarations,
     * each object is used to store 1 image.
     */
    public static BufferedImage rktS1;           // Earth stg 1
    public static BufferedImage rktS2;           // Earth stg 2
    public static BufferedImage rktS3;           // Earth stg 3
    public static BufferedImage rktCone;         // Earth top cone
    public static BufferedImage rktBurn;         // Earth rocket fire burn
    public static BufferedImage rktFlame;        // Rocket flame
    public static BufferedImage tower;           // Launch tower
    public static BufferedImage lPad;            // Launch pad

    public static BufferedImage rktDef;          // Dot representing pos of rocket

    public static BufferedImage earth;
    public static BufferedImage moon;

    public static BufferedImage stars;           // Stars
    public static BufferedImage skyGrad;         // Sky

    public static BufferedImage rkt;             // Rocket body while payload ejection
    public static BufferedImage rktCovUp;        // Payload Upper Cover
    public static BufferedImage rktCovDown;      // Payload Lower Cover
    public static BufferedImage payLoad;         // Payload ie Module and lander
    public static BufferedImage landerBrkOff;    // lander breaks off module
    public static BufferedImage moduleRsld;      // Module released of lander and left behind
    public static BufferedImage landerRsldF;     // lander released, eng off
    public static BufferedImage landerRsldB;     // lander released, eng burn
    public static BufferedImage lander64F;       // lander lands seen from dist, eng off
    public static BufferedImage lander64B;       // lander lands seen from dist, eng burn
    public static BufferedImage lander128F;      // lander lands seen from close, eng off
    public static BufferedImage lander128B;      // lander lands seen from close, eng burn
    public static BufferedImage lunSurface;

    public static BufferedImage success;
    public static BufferedImage failure;

    // Global object declaration of SpriteSheet class.
    private static SpriteSheet lander0x0, launchPad12x2, launchtower1x7, module4x3, payloadRsl0x0, planets4x4, points, releases4x3, rockets1x7;

    // Global variable declaratons.
    private static final int WIDTH = 64, HEIGHT = 64;

    public static void initiate()
    {
        if (init) return;
        else init = true;

        // Define SpriteSheet object.
        launchPad12x2 = new SpriteSheet(ImageLoader.loadImage("res/launchPad12x2.png"));
        launchtower1x7 = new SpriteSheet(ImageLoader.loadImage("res/launchtower1x7.png"));
        payloadRsl0x0 = new SpriteSheet(ImageLoader.loadImage("res/payloadRsl0x0.png"));
        points = new SpriteSheet(ImageLoader.loadImage("res/points.png"));
        releases4x3 = new SpriteSheet(ImageLoader.loadImage("res/releases4x3.png"));
        rockets1x7 = new SpriteSheet(ImageLoader.loadImage("res/rockets1x7.png"));

        // Assign images to BufferedImage objects.
        rktCone = rockets1x7.Crop(0*WIDTH, 0*HEIGHT, 1*WIDTH, 1*HEIGHT);
        rktS3 = rockets1x7.Crop(0*WIDTH, 1*HEIGHT, 1*WIDTH, 2*HEIGHT);
        rktS2 = rockets1x7.Crop(0*WIDTH, 3*HEIGHT, 1*WIDTH, 2*HEIGHT);
        rktS1 = rockets1x7.Crop(0*WIDTH, 5*HEIGHT, 1*WIDTH, 2*HEIGHT);
        rktBurn = rockets1x7.Crop(0*WIDTH, 7*HEIGHT, 1*WIDTH, 2*HEIGHT);
        rktFlame = new SpriteSheet(ImageLoader.loadImage("res/flame.png")).Crop(0 * WIDTH, 0 * HEIGHT, 32, 95);

        tower = launchtower1x7.Crop(0*WIDTH, 0*HEIGHT, 1*WIDTH, 7*HEIGHT);

        lPad = launchPad12x2.Crop(0*WIDTH, 0*HEIGHT, 12*WIDTH, 2*HEIGHT);

        rktDef = points.Crop(0, 0, 4, 4);
        earth = points.Crop(5, 0, 16, 16);
        moon = points.Crop(22, 0, 114, 114);

        stars = ImageLoader.loadImage("res/stars.png");
        skyGrad = ImageLoader.loadImage("res/skyGrad.png");

        rkt = payloadRsl0x0.Crop(0*WIDTH, 0*HEIGHT, 7*WIDTH, 5*HEIGHT);
        rktCovUp = payloadRsl0x0.Crop(7*WIDTH, 0*HEIGHT, 7*WIDTH, 2*HEIGHT);
        rktCovDown = payloadRsl0x0.Crop(7*WIDTH, 3*HEIGHT, 7*WIDTH, 2*HEIGHT);

        payLoad = releases4x3.Crop(0*WIDTH, 0*HEIGHT, 7*WIDTH, 3*HEIGHT);
        landerBrkOff = releases4x3.Crop(1*WIDTH, 3*HEIGHT, 4*WIDTH, 3*HEIGHT);
        moduleRsld = releases4x3.Crop(1*WIDTH, 6*HEIGHT, 4*WIDTH, 3*HEIGHT);

        /*landerRsldF = lander0x0.Crop(1*WIDTH, 7*HEIGHT, 2*WIDTH, 2*HEIGHT);
        landerRsldB = lander0x0.Crop(1*WIDTH, 1*HEIGHT, 2*WIDTH, 2*HEIGHT);
        lander64F = lander0x0.Crop(0*WIDTH, 5*HEIGHT, 1*WIDTH, 1*HEIGHT);
        lander64B = lander0x0.Crop(0*WIDTH, 7*HEIGHT, 1*WIDTH, 1*HEIGHT);
        lander128F = lander0x0.Crop(1*WIDTH, 3*HEIGHT, 2*WIDTH, 2*HEIGHT);
        lander128B = lander0x0.Crop(1*WIDTH, 5*HEIGHT, 2*WIDTH, 2*HEIGHT);*/
    }
}
