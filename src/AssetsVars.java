public class AssetsVars
{
    // running flag
    public static boolean running = false;

    // boolean markers
    public static Activities activity = Activities.BELOW_KARMAN;

    // frames, updates and observation clock
    public static final int FPS = 60;
    public static final int UPS = 60;
    public static final double SEC_PER_FRAME = 1/60;
    public static double obsTime = 0;

    // Time warp factor
    public static double warpF = 1;

    // scale factor
    public static final double MTR_PER_PIXEL = 5.7657657657657655;
    public static final double PIXEL_PER_MTR = 0.1734375;
    public static final double SCALE_FACTOR = PIXEL_PER_MTR;

    // Variables for Rocket equation
    public static final double EARTH_G_M = 6.6726 * Math.exp(-11) * 5.972 * Math.exp(24);  // G * massOfEarth
    public static final int EARTH_RADIUS = 6371000;                                        // 6.371 million m
    public static final int ROCKET_MAX_THRUST = 34000000;                                  // 34 million N
    public static final int ROCKET_DRY_MASS = 1300000;                                     // 1.3 million kg
    public static final int ROCKET_MAX_FUEL = 1000000;                                     // 1 million kg
    public static final int ROCKET_INIT_MASS = ROCKET_DRY_MASS + ROCKET_MAX_FUEL;          // 2.3 million kg
}
