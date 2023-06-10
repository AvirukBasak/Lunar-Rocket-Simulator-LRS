import java.awt.image.BufferedImage;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.ArrayList;

public class Environment
{
    public static enum Pov {
        ENTITY_POV,
        ENVIRONMENT_POV,
        FOCUSSED_POV,
        EXTERNAL_POV
    }

    private int width, height;
    private boolean hasGround;
    private BufferedImage bg;
    private BufferStrategy bs;

    private double x, y, dxEntity, dyEntity, ddxEntity, ddyEntity;
    private Pov pov;
    private Entity focussedEntity;
    protected List<Entity> entities;

    /**
     * A env is a collection of many entites.
     * @param Canvas The canvas where the env is drawn
     * @param BufferedImage The background image
     * @param double x coordinate of env bg
     * @param double y coordinate of env bg
     */
    public Environment(Canvas canvas, BufferedImage bg)
    {
        this.bg = bg;
        this.x = 0;
        this.y = 0;
        this.hasGround = false;
        this.pov = Pov.FOCUSSED_POV;
        this.dxEntity = 0;
        this.dyEntity = 0;
        this.ddxEntity = 0;
        this.ddyEntity = 0;
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
        this.bs = canvas.getBufferStrategy();
        if (this.bs == null) {
            canvas.createBufferStrategy(3);
            this.bs = canvas.getBufferStrategy();
        }
        this.focussedEntity = null;
        this.entities = null;
    }
    /**
     * A env is a collection of many entites.
     * @param Canvas The canvas where the env is drawn
     * @param BufferedImage The background image
     * @param double x coordinate of env bg
     * @param double y coordinate of env bg
     */
    public Environment(Canvas canvas, BufferedImage bg, double x, double y)
    {
        this(canvas, bg);
        this.x = x;
        this.y = y;
    }
    /**
     * A env is a collection of many entites.
     * @param Canvas The canvas where the env is drawn
     * @param BufferedImage The background image
     * @param double default x acceleration of that system due to some potential gradient, pixel/frame2
     * @param double default y acceleration of that system due to some potential gradient, pixel/frame2
     */
    public Environment(Canvas canvas, BufferedImage bg, double ddx, double ddy, boolean hasGround)
    {
        this(canvas, bg);
        this.hasGround = hasGround;
        this.ddxEntity = ddx;
        this.ddyEntity = ddy;
    }
    /**
     * A env is a collection of many entites.
     * @param Canvas The canvas where the env is drawn
     * @param BufferedImage The background image
     * @param double x coordinate of env bg
     * @param double y coordinate of env bg
     * @param double default x acceleration of that system due to some potential gradient, pixel/frame2
     * @param double default y acceleration of that system due to some potential gradient, pixel/frame2
     */
    public Environment(Canvas canvas, BufferedImage bg, double x, double y, double ddx, double ddy, boolean hasGround)
    {
        this(canvas, bg, x, y);
        this.hasGround = hasGround;
        this.ddxEntity = ddx;
        this.ddyEntity = ddy;
    }
    /**
     * Add an entity to the env.
     * @param Entity
     */
    public void addEntity(Entity e)
    {
        e.env = this;
        if (this.entities == null)
            this.entities = new ArrayList<Entity>();
        this.entities.add(e);
    }
    /**
     * Update the env, keeps entities static.
     * Useful for motion in POV of entities.
     * @param double delta-x
     * @param double delta-y
     * @param Environment.Pov pov, pass Pov.FOCUSSED_POV, Pov.ENTITY_POV or Pov.ENVIRONMENT_POV
     */
    public void update(double dx, double dy, Pov pov)
    {
        if (pov == Pov.FOCUSSED_POV) {
            this.x += dx;
            this.y += dy;
            if (this.entities != null)
                for (Entity e : this.entities)
                    if (e != this.focussedEntity) e.update(dx, dy);
        } else {
            if (pov == Pov.EXTERNAL_POV || pov == Pov.ENTITY_POV) {
                this.x += dx;
                this.y += dy;
            }
            if (pov == Pov.EXTERNAL_POV || pov == Pov.ENVIRONMENT_POV) {
                if (this.entities != null)
                    for (Entity e : this.entities)
                        e.update(dx, dy);
            }
        }
    }
    /**
     * Update the env, keeps entities static.
     * @param double delta-x
     * @param double delta-y
     */
    public void update(double dx, double dy) { this.update(dx, dy, this.pov); }
    /**
     * Change POV of motion.
     * @param Environment.Pov pov, pass Pov.FOCUSSED_POV, Pov.ENTITY_POV or Pov.ENVIRONMENT_POV
    `*/
    public void switchPOV(Pov pov)
    {
        this.pov = pov;
    }
    /**
     * Focus on an entity.
     * Auto switches POV to Pov.FUCUSSED_POV.
     */
    public void focusOn(Entity e)
    {
        this.pov = Pov.FOCUSSED_POV;
        this.focussedEntity = e;
    }
    /**
     * Update the env by absolute position.
     * @param double new-x
     * @param double new-y
     */
    public void move(double x, double y)
    {
        double dx = x - this.x;
        double dy = y - this.y;
        this.update(dx, dy);
    }
    /**
     * Render env with the entities.
     */
    public void render()
    {
        Graphics gfx = this.bs.getDrawGraphics();
        gfx.clearRect(0, 0, this.width, this.height);
        gfx.drawImage(this.bg, (int) this.x, (int) this.y, null);
        if (this.entities != null)
            for (Entity e : this.entities)
                e.render(gfx);
        this.bs.show();
        Toolkit.getDefaultToolkit().sync();
        gfx.dispose();
    }
    /**
     * Takes care of the 'default force' in the environment.
     */
    public void defForceUpdate()
    {
        if (this.pov == Pov.FOCUSSED_POV || this.pov == Pov.ENTITY_POV)
            if (!(this.hasGround && this.y + this.bg.getHeight() <= this.height)) {
                this.x += -this.dxEntity;
                this.y += -this.dyEntity;
            } else if (this.pov != Pov.ENVIRONMENT_POV)
                this.switchPOV(Pov.ENVIRONMENT_POV);
        else if (this.pov == Pov.ENVIRONMENT_POV)
            if (this.entities != null)
                for (Entity e : this.entities)
                    if (!(this.hasGround && e.getY() + e.getHeight() >= this.height))
                        e.update(this.dxEntity, this.dyEntity);
        this.dxEntity += this.ddxEntity;
        this.dyEntity += this.ddyEntity;
    }
    /**
     * Get graphics object
     */
    public Graphics getGfx()
    {
        return this.bs.getDrawGraphics();
    }
    /**
     * Flush env with bg and all resources of its entities.
     */
    public void flush()
    {
        this.bg.flush();
        if (this.entities != null)
            for (Entity e : this.entities)
                e.flush();
        this.entities = null;
    }

    public int getWidth()
    {
        return this.bg.getWidth();
    }
    public int getHeight()
    {
        return this.bg.getHeight();
    }

    public int getX()
    {
        return (int) this.x;
    }
    public int getY()
    {
        return (int) this.y;
    }
}
