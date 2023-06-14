package assets;

import java.awt.image.BufferedImage;

import util.ImageLoader;
import util.SpriteSheet;

public class Sprites
{
    private static boolean init = false;
    /* Global BufferedImage object declarations,
     * each object is used to store 1 image.
     */
    private static BufferedImage m_rktS1;           // Earth stg 1
    private static BufferedImage m_rktS2;           // Earth stg 2
    private static BufferedImage m_rktS3;           // Earth stg 3
    private static BufferedImage m_rktCone;         // Earth top cone
    private static BufferedImage m_rktBurn;         // Earth rocket fire burn
    private static BufferedImage m_rktFlame;        // Rocket flame
    private static BufferedImage m_tower;           // Launch tower
    private static BufferedImage m_lPad;            // Launch pad

    private static BufferedImage m_rktDef;          // Dot representing pos of rocket

    private static BufferedImage m_earth;
    private static BufferedImage m_moon;

    private static BufferedImage m_stars;           // Stars
    private static BufferedImage m_skyGrad;         // Sky

    private static BufferedImage m_rkt;             // Rocket body while payload ejection
    private static BufferedImage m_rktCovUp;        // Payload Upper Cover
    private static BufferedImage m_rktCovDown;      // Payload Lower Cover
    private static BufferedImage m_payLoad;         // Payload ie Module and lander
    private static BufferedImage m_landerBrkOff;    // lander breaks off module
    private static BufferedImage m_moduleRsld;      // Module released of lander and left behind
    private static BufferedImage m_landerRsldF;     // lander released, eng off
    private static BufferedImage m_landerRsldB;     // lander released, eng burn
    private static BufferedImage m_lander64F;       // lander lands seen from dist, eng off
    private static BufferedImage m_lander64B;       // lander lands seen from dist, eng burn
    private static BufferedImage m_lander128F;      // lander lands seen from close, eng off
    private static BufferedImage m_lander128B;      // lander lands seen from close, eng burn
    private static BufferedImage m_lunSurface;

    private static BufferedImage m_success;
    private static BufferedImage m_failure;

    // Global object declaration of SpriteSheet class.
    private static SpriteSheet m_lander0x0,
                               m_launchPad12x2,
                               m_launchtower1x7,
                               m_module4x3,
                               m_payloadRsl0x0,
                               m_planets4x4,
                               m_points,
                               m_releases4x3,
                               m_rockets1x7;

    // Global variable declaratons.
    private static final int WIDTH = 64, HEIGHT = 64;

    public static void initiate()
    {
        if (init) return;
        else init = true;

        m_lander0x0 = null;
        m_launchPad12x2 = null;
        m_launchtower1x7 = null;
        m_module4x3 = null;
        m_payloadRsl0x0 = null;
        m_planets4x4 = null;
        m_points = null;
        m_releases4x3 = null;
        m_rockets1x7 = null;

        m_rktS1 = null;
        m_rktS2 = null;
        m_rktS3 = null;
        m_rktCone = null;
        m_tower = null;
        m_lPad = null;
        m_rktDef = null;
        m_earth = null;
        m_moon = null;
        m_rkt = null;
        m_rktCovUp = null;
        m_rktCovDown = null;
        m_payLoad = null;
        m_landerBrkOff = null;
        m_moduleRsld = null;
        m_landerRsldF = null;
        m_landerRsldB = null;
        m_lander64F = null;
        m_lander64B = null;
        m_lander128F = null;
        m_lander128B = null;
        m_lunSurface = null;
    }

    public static BufferedImage rktCone() {
        if (m_rockets1x7 == null) m_rockets1x7 = new SpriteSheet(ImageLoader.loadImage("res/rockets1x7.png"));
        if (m_rktCone == null) return m_rktCone = m_rockets1x7.crop(0*WIDTH, 0*HEIGHT, 1*WIDTH, 1*HEIGHT);
        return m_rktCone;
    }

    public static BufferedImage rktS3() {
        if (m_rockets1x7 == null) m_rockets1x7 = new SpriteSheet(ImageLoader.loadImage("res/rockets1x7.png"));
        if (m_rktS3 == null) return m_rktS3 = m_rockets1x7.crop(0*WIDTH, 1*HEIGHT, 1*WIDTH, 2*HEIGHT);
        return m_rktS3;
    }

    public static BufferedImage rktS2() {
        if (m_rockets1x7 == null) m_rockets1x7 = new SpriteSheet(ImageLoader.loadImage("res/rockets1x7.png"));
        if (m_rktS2 == null) return m_rktS2 = m_rockets1x7.crop(0*WIDTH, 3*HEIGHT, 1*WIDTH, 2*HEIGHT);
        return m_rktS2;
    }

    public static BufferedImage rktS1() {
        if (m_rockets1x7 == null) m_rockets1x7 = new SpriteSheet(ImageLoader.loadImage("res/rockets1x7.png"));
        if (m_rktS1 == null) return m_rktS1 = m_rockets1x7.crop(0*WIDTH, 5*HEIGHT, 1*WIDTH, 2*HEIGHT);
        return m_rktS1;
    }

    public static BufferedImage rktBurn() {
        if (m_rockets1x7 == null) m_rockets1x7 = new SpriteSheet(ImageLoader.loadImage("res/rockets1x7.png"));
        if (m_rktBurn == null) return m_rktBurn = m_rockets1x7.crop(0*WIDTH, 7*HEIGHT, 1*WIDTH, 2*HEIGHT);
        return m_rktBurn;
    }

    public static BufferedImage rktFlame() {
        if (m_rktFlame == null) return m_rktFlame = new SpriteSheet(ImageLoader.loadImage("res/flame.png")).crop(0 * WIDTH, 0 * HEIGHT, 32, 95);
        return m_rktFlame;
    }

    public static BufferedImage tower() {
        if (m_launchtower1x7 == null) m_launchtower1x7 = new SpriteSheet(ImageLoader.loadImage("res/launchtower1x7.png"));
        if (m_tower == null) return m_tower = m_launchtower1x7.crop(0*WIDTH, 0*HEIGHT, 1*WIDTH, 7*HEIGHT -1);
        return m_tower;
    }

    public static BufferedImage lPad() {
        if (m_launchPad12x2 == null) m_launchPad12x2 = new SpriteSheet(ImageLoader.loadImage("res/launchPad12x2.png"));
        if (m_lPad == null) return m_lPad = m_launchPad12x2.crop(0*WIDTH, 0*HEIGHT, 12*WIDTH, 2*HEIGHT);
        return m_lPad;
    }


    public static BufferedImage rktDef() {
        if (m_points == null) m_points = new SpriteSheet(ImageLoader.loadImage("res/points.png"));
        if (m_rktDef == null) return m_rktDef = m_points.crop(0, 0, 4, 4);
        return m_rktDef;
    }

    public static BufferedImage earth() {
        if (m_points == null) m_points = new SpriteSheet(ImageLoader.loadImage("res/points.png"));
        if (m_earth == null) return m_earth = m_points.crop(5, 0, 16, 16);
        return m_earth;
    }

    public static BufferedImage moon() {
        if (m_points == null) m_points = new SpriteSheet(ImageLoader.loadImage("res/points.png"));
        if (m_moon == null) return m_moon = m_points.crop(22, 0, 114, 114);
        return m_moon;
    }

    public static BufferedImage stars() {
        if (m_stars == null) return m_stars = ImageLoader.loadImage("res/stars.png");
        return m_stars;
    }

    public static BufferedImage skyGrad() {
        if (m_skyGrad == null) return m_skyGrad = ImageLoader.loadImage("res/skyGrad.png");
        return m_skyGrad;
    }

    public static BufferedImage rkt() {
        if (m_payloadRsl0x0 == null) m_payloadRsl0x0 = new SpriteSheet(ImageLoader.loadImage("res/payloadRsl0x0.png"));
        if (m_rkt == null) return m_rkt = m_payloadRsl0x0.crop(0*WIDTH, 0*HEIGHT, 7*WIDTH, 5*HEIGHT);
        return m_rkt;
    }

    public static BufferedImage rktCovUp() {
        if (m_payloadRsl0x0 == null) m_payloadRsl0x0 = new SpriteSheet(ImageLoader.loadImage("res/payloadRsl0x0.png"));
        if (m_rktCovUp == null) return m_rktCovUp = m_payloadRsl0x0.crop(7*WIDTH, 0*HEIGHT, 7*WIDTH, 2*HEIGHT);
        return m_rktCovUp;
    }

    public static BufferedImage rktCovDown() {
        if (m_payloadRsl0x0 == null) m_payloadRsl0x0 = new SpriteSheet(ImageLoader.loadImage("res/payloadRsl0x0.png"));
        if (m_rktCovDown == null) return m_rktCovDown = m_payloadRsl0x0.crop(7*WIDTH, 3*HEIGHT, 7*WIDTH, 2*HEIGHT);
        return m_rktCovDown;
    }


    public static BufferedImage payLoad() {
        if (m_releases4x3 == null) m_releases4x3 = new SpriteSheet(ImageLoader.loadImage("res/releases4x3.png"));
        if (m_payLoad == null) return m_payLoad = m_releases4x3.crop(0*WIDTH, 0*HEIGHT, 7*WIDTH, 3*HEIGHT);
        return m_payLoad;
    }

    public static BufferedImage landerBrkOff() {
        if (m_releases4x3 == null) m_releases4x3 = new SpriteSheet(ImageLoader.loadImage("res/releases4x3.png"));
        if (m_landerBrkOff == null) return m_landerBrkOff = m_releases4x3.crop(1*WIDTH, 3*HEIGHT, 4*WIDTH, 3*HEIGHT);
        return m_landerBrkOff;
    }

    public static BufferedImage moduleRsld() {
        if (m_releases4x3 == null) m_releases4x3 = new SpriteSheet(ImageLoader.loadImage("res/releases4x3.png"));
        if (m_moduleRsld == null) return m_moduleRsld = m_releases4x3.crop(1*WIDTH, 6*HEIGHT, 4*WIDTH, 3*HEIGHT);
        return m_moduleRsld;
    }

/* public static BufferedImage landerRsldF() {
        if (m_landerRsldF == null) return m_landerRsldF = m_lander0x0.crop(1*WIDTH, 7*HEIGHT, 2*WIDTH, 2*HEIGHT);
        return m_landerRsldF;
    }

    public static BufferedImage landerRsldB() {
        if (m_landerRsldB == null) return m_landerRsldB = m_lander0x0.crop(1*WIDTH, 1*HEIGHT, 2*WIDTH, 2*HEIGHT);
        return m_landerRsldB;
    }

    public static BufferedImage lander64F() {
        if (m_lander64F == null) return m_lander64F = m_lander0x0.crop(0*WIDTH, 5*HEIGHT, 1*WIDTH, 1*HEIGHT);
        return m_lander64F;
    }

    public static BufferedImage lander64B() {
        if (m_lander64B == null) return m_lander64B = m_lander0x0.crop(0*WIDTH, 7*HEIGHT, 1*WIDTH, 1*HEIGHT);
        return m_lander64B;
    }

    public static BufferedImage lander128F() {
        if (m_lander128F == null) return m_lander128F = m_lander0x0.crop(1*WIDTH, 3*HEIGHT, 2*WIDTH, 2*HEIGHT);
        return m_lander128F;
    }

    public static BufferedImage lander128B() {
        if (m_lander128B == null) return m_lander128B = m_lander0x0.crop(1*WIDTH, 5*HEIGHT, 2*WIDTH, 2*HEIGHT);
        return m_lander128B;
    } */

    public static BufferedImage success() {
        // if (m_success == null) return m_success = ImageLoader.loadImage("res/success.png");
        return m_success;
    }

    public static BufferedImage failure() {
        // if (m_failure == null) return m_failure = ImageLoader.loadImage("res/failure.png");
        return m_failure;
    }
}
