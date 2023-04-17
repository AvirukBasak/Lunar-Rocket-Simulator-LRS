public class AssetsVars
{
    // quit flag
    protected static boolean quit = false;

    // boolean markers
    protected static Activities activity = Activities.BELOW_KARMAN;

    // frames, updates and observation clock
    protected static final int FPS = 60;
    protected static final int UPS = 60;
    static protected final double secPerFrame = 1/60;
    protected static double obsTime = 0;

    // Time warp factor
    protected static double warpF = 1;

    // scale factor
    static final double meterPerPixel = 5.7657657657657655;
    static final double pixelPerMeter = 0.1734375;
    static final double scaleF = pixelPerMeter;

    // Variables for Rocket equation
    protected static final double G_mEarth = 6.6726 * Math.exp(-11) * 5.972 * Math.exp(24);  // G * Mass(earth)
    protected static final int radEarth = 6371000;        // radius of earth in m, 6.3 million m
    protected static final int thrustMAX = 34000000;      // max thrust 34 million N
    protected static final int mDry = 1300000;            // mass dry in kg, 1.3 million kg
    protected static final int mFuelMax = 1000000;        // mass of fuel in kg, init 1 million kg
    protected static final int m0 = mDry + mFuelMax;      // mass initial in kg, 2.3 million kg
}
