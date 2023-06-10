package env;

import java.awt.Canvas;

import assets.Sprites;
import assets.StateVars;

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
        Sprites.initiate();
        m_stars = null;
        m_skyGrad = null;
        m_success = null;
        m_failure = null;
    }

    public static Environment stars(Canvas cv)
    {
        if (m_stars == null)
            return m_stars = new Environment(cv, Sprites.stars);
        return m_stars;
    }

    public static Environment skyGrad(Canvas cv)
    {
        if (m_skyGrad == null)
            return m_skyGrad = new Environment(cv, Sprites.skyGrad, 0,
                10 * StateVars.PIXEL_PER_MTR / (StateVars.FPS * StateVars.FPS), true);
        return m_skyGrad;
    }

    public static Environment success(Canvas cv)
    {
        if (m_success == null)
            return m_success = new Environment(cv, Sprites.success);
        return m_success;
    }

    public static Environment failure(Canvas cv)
    {
        if (m_failure == null)
            return m_failure = new Environment(cv, Sprites.failure);
        return m_failure;
    }

    public static Environment stars(Canvas cv, double x, double y)
    {
        if (m_stars == null)
            return m_stars = new Environment(cv, Sprites.stars, x, y);
        m_stars.move(x, y);
        return m_stars;
    }

    public static Environment skyGrad(Canvas cv, double x, double y)
    {
        if (m_skyGrad == null)
            return m_skyGrad = new Environment(cv, Sprites.skyGrad, x, y, 0,
                10 * StateVars.PIXEL_PER_MTR / (StateVars.FPS * StateVars.FPS), true);
        m_skyGrad.move(x, y);
        return m_skyGrad;
    }

    public static Environment success(Canvas cv, double x, double y)
    {
        if (m_success == null)
            return m_success = new Environment(cv, Sprites.success, x, y);
        m_success.move(x, y);
        return m_success;
    }

    public static Environment failure(Canvas cv, double x, double y)
    {
        if (m_failure == null)
            return m_failure = new Environment(cv, Sprites.failure, x, y);
        m_failure.move(x, y);
        return m_failure;
    }
}
