import java.awt.image.BufferedImage;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.ArrayList;

public class Environment
{
    protected int width, height;
    protected boolean hasGround;
    protected BufferedImage bg;
    protected BufferStrategy bs;

    protected double x, y, dxEntity, dyEntity, ddxEntity, ddyEntity;
    protected List<Entity> entities;

    /**
     * A env is a collection of many entites.
     * @param Canvas The canvas where the env is drawn
     * @param BufferedImage The background image
     * @param double x coordinate of env bg
     * @param double y coordinate of env bg
     */
    public Environment(Canvas canvas, BufferedImage bg, double x, double y)
    {
        this.bg = bg;
        this.x = x;
        this.y = y;
        this.hasGround = false;
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
        this.entities = null;
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
        this.bg = bg;
        this.x = x;
        this.y = y;
        this.hasGround = hasGround;;
        this.dxEntity = 0;
        this.dyEntity = 0;
        this.ddxEntity = ddx;
        this.ddyEntity = ddy;
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
        this.bs = canvas.getBufferStrategy();
        if (this.bs == null) {
            canvas.createBufferStrategy(3);
            this.bs = canvas.getBufferStrategy();
        }
        this.entities = null;
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
     */
    public void update(double dx, double dy)
    {
        this.x += dx;
        this.y += dy;
    }
    /**
     * Update the env with entites.
     * Useful for motion in POV of player.
     * @param double delta-x
     * @param double delta-y
     * @param boolean movEntities
     */
    public void update(double dx, double dy, boolean movEntities)
    {
        this.x += dx;
        this.y += dy;
        if (movEntities && this.entities != null)
            for (Entity e : this.entities)
                e.update(dx, dy);
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
     * Update the env with enties by absolute position.
     * @param double new-x
     * @param double new-y
     * @param boolean movEntities
     */
    public void move(double x, double y, boolean movEntities)
    {
        double dx = x - this.x;
        double dy = y - this.y;
        this.update(dx, dy, true);
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
            for (Entity e : this.entities) {
                if (this.hasGround
                    && e.y + e.entityImg.getHeight() >= this.height) continue;
                e.update(this.dxEntity, this.dyEntity);
                this.dxEntity += this.ddxEntity;
                this.dyEntity += this.ddyEntity;
                e.render(gfx);
            }
        this.bs.show();
        Toolkit.getDefaultToolkit().sync();
        gfx.dispose();
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
}
