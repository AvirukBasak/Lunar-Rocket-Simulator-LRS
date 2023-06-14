package entity;

import assets.Sprites;

public class Entities
{
    private static boolean init = false;

    private static Entity m_rktS1;           // Earth stg 1
    private static Entity m_rktS2;           // Earth stg 2
    private static Entity m_rktS3;           // Earth stg 3
    private static Entity m_rktCone;         // Earth top cone
    private static Entity m_tower;           // Launch tower
    private static Entity m_lPad;            // Launch pad
    private static Entity m_rktDef;          // Dot representing pos of rocket
    private static Entity m_earth;
    private static Entity m_moon;
    private static Entity m_rkt;             // Rocket body while payload ejection
    private static Entity m_rktCovUp;        // Payload Upper Cover
    private static Entity m_rktCovDown;      // Payload Lower Cover
    private static Entity m_payLoad;         // Payload ie Module and lander
    private static Entity m_landerBrkOff;    // lander breaks off module
    private static Entity m_moduleRsld;      // Module released of lander and left behind
    private static Entity m_landerRsldF;     // lander released, eng off
    private static Entity m_landerRsldB;     // lander released, eng burn
    private static Entity m_lander64F;       // lander lands seen from dist, eng off
    private static Entity m_lander64B;       // lander lands seen from dist, eng burn
    private static Entity m_lander128F;      // lander lands seen from close, eng off
    private static Entity m_lander128B;      // lander lands seen from close, eng burn
    private static Entity m_lunSurface;      // moon surface

    public static void initiate()
    {
        if (init) return;
        else init = true;
        Sprites.initiate();
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

    public static Entity rktS1()
    {
        // if (m_rktS1 == null) NullPointerException("Entities: object 'rktS1' is null");
        if (m_rktS1 == null) return m_rktS1 = new Entity(Sprites.rktS1());
        else return m_rktS1;
    }

    public static Entity rktS2()
    {
        // if (m_rktS2 == null) NullPointerException("Entities: object 'rktS2' is null");
        if (m_rktS2 == null) return m_rktS2 = new Entity(Sprites.rktS2());
        else return m_rktS2;
    }

    public static Entity rktS3()
    {
        // if (m_rktS3 == null) NullPointerException("Entities: object 'rktS3' is null");
        if (m_rktS3 == null) return m_rktS3 = new Entity(Sprites.rktS3());
        else return m_rktS3;
    }

    public static Entity rktCone()
    {
        // if (m_rktCone == null) NullPointerException("Entities: object 'rktCone' is null");
        if (m_rktCone == null) return m_rktCone = new Entity(Sprites.rktCone());
        else return m_rktCone;
    }

    public static Entity tower()
    {
        // if (m_tower == null) NullPointerException("Entities: object 'tower' is null");
        if (m_tower == null) return m_tower = new Entity(Sprites.tower());
        else return m_tower;
    }

    public static Entity lPad()
    {
        // if (m_lPad == null) NullPointerException("Entities: object 'lPad' is null");
        if (m_lPad == null) return m_lPad = new Entity(Sprites.lPad());
        else return m_lPad;
    }

    public static Entity rktDef()
    {
        // if (m_rktDef == null) NullPointerException("Entities: object 'rktDef' is null");
        if (m_rktDef == null) return m_rktDef = new Entity(Sprites.rktDef());
        else return m_rktDef;
    }

    public static Entity earth()
    {
        // if (m_earth == null) NullPointerException("Entities: object 'earth' is null");
        if (m_earth == null) return m_earth = new Entity(Sprites.earth());
        else return m_earth;
    }

    public static Entity moon()
    {
        // if (m_moon == null) NullPointerException("Entities: object 'moon' is null");
        if (m_moon == null) return m_moon = new Entity(Sprites.moon());
        else return m_moon;
    }

    public static Entity rkt()
    {
        // if (m_rkt == null) NullPointerException("Entities: object 'rkt' is null");
        if (m_rkt == null) return m_rkt = new Entity(Sprites.rkt());
        else return m_rkt;
    }

    public static Entity rktCovUp()
    {
        // if (m_rktCovUp == null) NullPointerException("Entities: object 'rktCovUp' is null");
        if (m_rktCovUp == null) return m_rktCovUp = new Entity(Sprites.rktCovUp());
        else return m_rktCovUp;
    }

    public static Entity rktCovDown()
    {
        // if (m_rktCovDown == null) NullPointerException("Entities: object 'rktCovDown' is null");
        if (m_rktCovDown == null) return m_rktCovDown = new Entity(Sprites.rktCovDown());
        else return m_rktCovDown;
    }

    public static Entity payLoad()
    {
        // if (m_payLoad == null) NullPointerException("Entities: object 'payLoad' is null");
        if (m_payLoad == null) return m_payLoad = new Entity(Sprites.payLoad());
        else return m_payLoad;
    }

    public static Entity landerBrkOff()
    {
        // if (m_landerBrkOff == null) NullPointerException("Entities: object 'landerBrkOff' is null");
        if (m_landerBrkOff == null) return m_landerBrkOff = new Entity(Sprites.landerBrkOff());
        else return m_landerBrkOff;
    }

    public static Entity moduleRsld()
    {
        // if (m_moduleRsld == null) NullPointerException("Entities: object 'moduleRsld' is null");
        if (m_moduleRsld == null) return m_moduleRsld = new Entity(Sprites.moduleRsld());
        else return m_moduleRsld;
    }

    public static Entity rktS1(double x, double y)
    {
        if (m_rktS1 == null) return m_rktS1 = new Entity(x, y, Sprites.rktS1());
        m_rktS1.move(x, y);
        return m_rktS1;
    }

    public static Entity rktS2(double x, double y)
    {
        if (m_rktS2 == null) return m_rktS2 = new Entity(x, y, Sprites.rktS2());
        m_rktS2.move(x, y);
        return m_rktS2;
    }

    public static Entity rktS3(double x, double y)
    {
        if (m_rktS3 == null) return m_rktS3 = new Entity(x, y, Sprites.rktS3());
        m_rktS3.move(x, y);
        return m_rktS3;
    }

    public static Entity rktCone(double x, double y)
    {
        if (m_rktCone == null) return m_rktCone = new Entity(x, y, Sprites.rktCone());
        m_rktCone.move(x, y);
        return m_rktCone;
    }

    public static Entity rktBurn(double x, double y)
    {
        final Entity rktBurn = new Entity(x, y, Sprites.rktBurn());
        rktBurn.isSoft = true;
        return rktBurn;
    }

    public static Entity rktFlame(double x, double y)
    {
        final Entity rktFlame = new Entity(x, y, Sprites.rktFlame());
        rktFlame.isSoft = true;
        return rktFlame;
    }

    public static Entity tower(double x, double y)
    {
        if (m_tower == null) return m_tower = new Entity(x, y, Sprites.tower());
        m_tower.move(x, y);
        return m_tower;
    }

    public static Entity lPad(double x, double y)
    {
        if (m_lPad == null) return m_lPad = new Entity(x, y, Sprites.lPad());
        m_lPad.move(x, y);
        return m_lPad;
    }

    public static Entity rktDef(double x, double y)
    {
        if (m_rktDef == null) return m_rktDef = new Entity(x, y, Sprites.rktDef());
        m_rktDef.move(x, y);
        return m_rktDef;
    }

    public static Entity earth(double x, double y)
    {
        if (m_earth == null) return m_earth = new Entity(x, y, Sprites.earth());
        m_earth.move(x, y);
        return m_earth;
    }

    public static Entity moon(double x, double y)
    {
        if (m_moon == null) return m_moon = new Entity(x, y, Sprites.moon());
        m_moon.move(x, y);
        return m_moon;
    }

    public static Entity rkt(double x, double y)
    {
        if (m_rkt == null) return m_rkt = new Entity(x, y, Sprites.rkt());
        m_rkt.move(x, y);
        return m_rkt;
    }

    public static Entity rktCovUp(double x, double y)
    {
        if (m_rktCovUp == null) return m_rktCovUp = new Entity(x, y, Sprites.rktCovUp());
        m_rktCovUp.move(x, y);
        return m_rktCovUp;
    }

    public static Entity rktCovDown(double x, double y)
    {
        if (m_rktCovDown == null) return m_rktCovDown = new Entity(x, y, Sprites.rktCovDown());
        m_rktCovDown.move(x, y);
        return m_rktCovDown;
    }

    public static Entity payLoad(double x, double y)
    {
        if (m_payLoad == null) return m_payLoad = new Entity(x, y, Sprites.payLoad());
        m_payLoad.move(x, y);
        return m_payLoad;
    }

    public static Entity landerBrkOff(double x, double y)
    {
        if (m_landerBrkOff == null) return m_landerBrkOff = new Entity(x, y, Sprites.landerBrkOff());
        m_landerBrkOff.move(x, y);
        return m_landerBrkOff;
    }

    public static Entity moduleRsld(double x, double y)
    {
        if (m_moduleRsld == null) return m_moduleRsld = new Entity(x, y, Sprites.moduleRsld());
        m_moduleRsld.move(x, y);
        return m_moduleRsld;
    }
}
