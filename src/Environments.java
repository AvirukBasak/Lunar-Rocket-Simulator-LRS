import java.awt.Canvas;

public class Environments
{
    protected static boolean init = false;
    protected static Environment m_stars;           // Stars
    protected static Environment m_skyGrad;         // Sky
    protected static Environment m_Success;
    protected static Environment m_Failure;

    public static void initiate()
    {
        if (init) return;
        else init = true;
        AssetsImg.initiate();
        m_stars = null;
        m_skyGrad = null;
        m_Success = null;
        m_Failure = null;
    }

    public static Environment stars(Canvas cv, int x, int y)
    {
        if (m_stars == null) return m_stars = new Environment(cv, AssetsImg.stars, x, y);
        else {
            m_stars.x = x;
            m_stars.y = y;
            return m_stars;
        }
    }

    public static Environment skyGrad(Canvas cv, int x, int y)
    {
        if (m_skyGrad == null)
            return m_skyGrad = new Environment(cv, AssetsImg.skyGrad, x, y, 10 *pixelPerMeter/(FPS*FPS), 10 *pixelPerMeter/(FPS*FPS), true);
        else {
            m_skyGrad.x = x;
            m_skyGrad.y = y;
            return m_skyGrad;
        }
    }

    public static Environment Success(Canvas cv, int x, int y)
    {
        if (m_Success == null) {
            m_Success = new Environment(cv, AssetsImg.Success, x, y);
            return m_Success;
        } else {
            m_Success.x = x;
            m_Success.y = y;
            return m_Success;
        }
    }

    public static Environment Failure(Canvas cv, int x, int y)
    {
        if (m_Failure == null) {
            m_Failure = new Environment(cv, AssetsImg.Failure, x, y);
            return m_Failure;
        } else {
            m_Failure.x = x;
            m_Failure.y = y;
            return m_Failure;
        }
    }
}
