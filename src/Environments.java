import java.awt.Canvas;

public class Environments
{
    private static boolean init = false;
    private static Environment m_stars;           // Stars
    private static Environment m_skyGrad;         // Sky
    private static Environment m_success;
    private static Environment m_failure;

    public static void initiate()
    {
        if (init) return;
        else init = true;
        AssetsImg.initiate();
        m_stars = null;
        m_skyGrad = null;
        m_success = null;
        m_failure = null;
    }

    public static Environment stars(Canvas cv, double x, double y)
    {
        if (m_stars == null) return m_stars = new Environment(cv, AssetsImg.stars, x, y);
        else {
            m_stars.move(x, y);
            return m_stars;
        }
    }

    public static Environment skyGrad(Canvas cv, double x, double y)
    {
        if (m_skyGrad == null)
            return m_skyGrad = new Environment(cv, AssetsImg.skyGrad, x, y, 0,
                10 * AssetsVars.PIXEL_PER_MTR / (AssetsVars.FPS * AssetsVars.FPS), true);
        else {
            m_skyGrad.move(x, y);
            return m_skyGrad;
        }
    }

    public static Environment success(Canvas cv, double x, double y)
    {
        if (m_success == null) {
            m_success = new Environment(cv, AssetsImg.success, x, y);
            return m_success;
        } else {
            m_success.move(x, y);
            return m_success;
        }
    }

    public static Environment failure(Canvas cv, double x, double y)
    {
        if (m_failure == null) {
            m_failure = new Environment(cv, AssetsImg.failure, x, y);
            return m_failure;
        } else {
            m_failure.move(x, y);
            return m_failure;
        }
    }
}
