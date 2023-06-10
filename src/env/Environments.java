package env;

import java.awt.Canvas;

import assets.Sprites;
import assets.StateVars;

public class Environments
{
    private static boolean init = false;
    private static Canvas cv;
    private static Environment m_stars;           // Stars
    private static Environment m_skyGrad;         // Sky
    private static Environment m_success;
    private static Environment m_failure;

    public static void initiate(Canvas cv)
    {
        if (init) return;
        else init = true;
        Sprites.initiate();
        Environments.cv = cv;
        m_stars = null;
        m_skyGrad = null;
        m_success = null;
        m_failure = null;
    }

    public static Environment stars()
    {
        if (m_stars == null)
            return m_stars = new Environment(cv, Sprites.stars);
        return m_stars;
    }

    public static Environment skyGrad()
    {
        if (m_skyGrad == null)
            return m_skyGrad = new Environment(cv, Sprites.skyGrad, 0,
                10 * StateVars.PIXEL_PER_MTR / (StateVars.FPS * StateVars.FPS), true);
        return m_skyGrad;
    }

    public static Environment success()
    {
        if (m_success == null)
            return m_success = new Environment(cv, Sprites.success);
        return m_success;
    }

    public static Environment failure()
    {
        if (m_failure == null)
            return m_failure = new Environment(cv, Sprites.failure);
        return m_failure;
    }

    public static Environment stars(double x, double y)
    {
        if (m_stars == null)
            return m_stars = new Environment(cv, Sprites.stars, x, y);
        m_stars.move(x, y);
        return m_stars;
    }

    public static Environment skyGrad(double x, double y)
    {
        if (m_skyGrad == null)
            return m_skyGrad = new Environment(cv, Sprites.skyGrad, x, y, 0,
                10 * StateVars.PIXEL_PER_MTR / (StateVars.FPS * StateVars.FPS), true);
        m_skyGrad.move(x, y);
        return m_skyGrad;
    }

    public static Environment success(double x, double y)
    {
        if (m_success == null)
            return m_success = new Environment(cv, Sprites.success, x, y);
        m_success.move(x, y);
        return m_success;
    }

    public static Environment failure(double x, double y)
    {
        if (m_failure == null)
            return m_failure = new Environment(cv, Sprites.failure, x, y);
        m_failure.move(x, y);
        return m_failure;
    }
}
