public class AssetsVars
{
    // quit flag
    protected static boolean quit = false;

    // boolean markers
    protected static Activities activity = Activities.BELOW_KARMAN;

    // frames, updates and observation clock
    protected static final int FPS = 60;
    protected static final int UPS = 60;
    static protected final double SEC_PER_FRAME = 1/60;
    protected static double obsTime = 0;

    // Time warp factor
    protected static double warpF = 1;

    // scale factor
    static final double MTR_PER_PIXEL = 5.7657657657657655;
    static final double PIXEL_PER_MTR = 0.1734375;
    static final double SCALE_FACTOR = PIXEL_PER_MTR;

    // Variables for Rocket equation
    protected static final double EARTH_G_M = 6.6726 * Math.exp(-11) * 5.972 * Math.exp(24);  // G * massOfEarth
    protected static final int EARTH_RADIUS = 6371000;                                        // 6.371 million m
    protected static final int ROCKET_MAX_THRUST = 34000000;                                  // 34 million N
    protected static final int ROCKET_DRY_MASS = 1300000;                                     // 1.3 million kg
    protected static final int ROCKET_MAX_FUEL = 1000000;                                     // 1 million kg
    protected static final int ROCKET_INIT_MASS = ROCKET_DRY_MASS + ROCKET_MAX_FUEL;          // 2.3 million kg
}
