public class Entities
{
    protected static boolean init = false;

    protected static Entity m_rktS1;           // Earth stg 1
    protected static Entity m_rktS2;           // Earth stg 2
    protected static Entity m_rktS3;           // Earth stg 3
    protected static Entity m_rktCone;         // Earth top cone
    protected static Entity m_tower;           // Launch tower
    protected static Entity m_lPad;            // Launch pad
    protected static Entity m_rktDef;          // Dot representing pos of rocket
    protected static Entity m_earth;
    protected static Entity m_moon;
    protected static Entity m_rkt;             // Rocket body while payload ejection
    protected static Entity m_rktCovUp;        // Payload Upper Cover
    protected static Entity m_rktCovDown;      // Payload Lower Cover
    protected static Entity m_payLoad;         // Payload ie Module and lander
    protected static Entity m_landerBrkOff;    // lander breaks off module
    protected static Entity m_moduleRsld;      // Module released of lander and left behind
    protected static Entity m_landerRsldF;     // lander released, eng off
    protected static Entity m_landerRsldB;     // lander released, eng burn
    protected static Entity m_lander64F;       // lander lands seen from dist, eng off
    protected static Entity m_lander64B;       // lander lands seen from dist, eng burn
    protected static Entity m_lander128F;      // lander lands seen from close, eng off
    protected static Entity m_lander128B;      // lander lands seen from close, eng burn
    protected static Entity m_lunSurface;      // moon surface

    public static void initiate()
    {
        if (init) return;
        else init = true;
        AssetsImg.initiate();
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

    public static Entity rktS1(double x, double y)
    {
        if (m_rktS1 == null) return m_rktS1 = new Entity(x, y, AssetsImg.rktS1);
        else {
            m_rktS1.x = x;
            m_rktS1.y = y;
            return m_rktS1;
        }
    }

    public static Entity rktS2(double x, double y)
    {
        if (m_rktS2 == null) return m_rktS2 = new Entity(x, y, AssetsImg.rktS2);
        else {
            m_rktS2.x = x;
            m_rktS2.y = y;
            return m_rktS2;
        }
    }

    public static Entity rktS3(double x, double y)
    {
        if (m_rktS3 == null) return m_rktS3 = new Entity(x, y, AssetsImg.rktS3);
        else {
            m_rktS3.x = x;
            m_rktS3.y = y;
            return m_rktS3;
        }
    }

    public static Entity rktCone(double x, double y)
    {
        if (m_rktCone == null) return m_rktCone = new Entity(x, y, AssetsImg.rktCone);
        else {
            m_rktCone.x = x;
            m_rktCone.y = y;
            return m_rktCone;
        }
    }

    public static Entity rktBurn(double x, double y)
    {
        final Entity rktBurn = new Entity(x, y, AssetsImg.rktBurn);
        rktBurn.isSoft = true;
        return rktBurn;
    }

    public static Entity rktFlame(double x, double y)
    {
        final Entity rktFlame = new Entity(x, y, AssetsImg.rktFlame);
        rktFlame.isSoft = true;
        return rktFlame;
    }

    public static Entity tower(double x, double y)
    {
        if (m_tower == null) return m_tower = new Entity(x, y, AssetsImg.tower);
        else {
            m_tower.x = x;
            m_tower.y = y;
            return m_tower;
        }
    }

    public static Entity lPad(double x, double y)
    {
        if (m_lPad == null) return m_lPad = new Entity(x, y, AssetsImg.lPad);
        else {
            m_lPad.x = x;
            m_lPad.y = y;
            return m_lPad;
        }
    }

    public static Entity rktDef(double x, double y)
    {
        if (m_rktDef == null) return m_rktDef = new Entity(x, y, AssetsImg.rktDef);
        else {
            m_rktDef.x = x;
            m_rktDef.y = y;
            return m_rktDef;
        }
    }

    public static Entity earth(double x, double y)
    {
        if (m_earth == null) return m_earth = new Entity(x, y, AssetsImg.earth);
        else {
            m_earth.x = x;
            m_earth.y = y;
            return m_earth;
        }
    }

    public static Entity moon(double x, double y)
    {
        if (m_moon == null) return m_moon = new Entity(x, y, AssetsImg.moon);
        else {
            m_moon.x = x;
            m_moon.y = y;
            return m_moon;
        }
    }

    public static Entity rkt(double x, double y)
    {
        if (m_rkt == null) return m_rkt = new Entity(x, y, AssetsImg.rkt);
        else {
            m_rkt.x = x;
            m_rkt.y = y;
            return m_rkt;
        }
    }

    public static Entity rktCovUp(double x, double y)
    {
        if (m_rktCovUp == null) return m_rktCovUp = new Entity(x, y, AssetsImg.rktCovUp);
        else {
            m_rktCovUp.x = x;
            m_rktCovUp.y = y;
            return m_rktCovUp;
        }
    }

    public static Entity rktCovDown(double x, double y)
    {
        if (m_rktCovDown == null) return m_rktCovDown = new Entity(x, y, AssetsImg.rktCovDown);
        else {
            m_rktCovDown.x = x;
            m_rktCovDown.y = y;
            return m_rktCovDown;
        }
    }

    public static Entity payLoad(double x, double y)
    {
        if (m_payLoad == null) return m_payLoad = new Entity(x, y, AssetsImg.payLoad);
        else {
            m_payLoad.x = x;
            m_payLoad.y = y;
            return m_payLoad;
        }
    }

    public static Entity landerBrkOff(double x, double y)
    {
        if (m_landerBrkOff == null) return m_landerBrkOff = new Entity(x, y, AssetsImg.landerBrkOff);
        else {
            m_landerBrkOff.x = x;
            m_landerBrkOff.y = y;
            return m_landerBrkOff;
        }
    }

    public static Entity moduleRsld(double x, double y)
    {
        if (m_moduleRsld == null) return m_moduleRsld = new Entity(x, y, AssetsImg.moduleRsld);
        else {
            m_moduleRsld.x = x;
            m_moduleRsld.y = y;
            return m_moduleRsld;
        }
    }
}
