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
    protected BufferedImage bg;
    protected BufferStrategy bs;

    protected int x, y;
    protected List<Entity> entities;

    /**
     * A env is a collection of many entites.
     * @param Canvas The canvas where the env is drawn
     * @param BufferedImage The background image
     * @param int x coordinate of env bg
     * @param int y coordinate of env bg
     */
    public Environment(Canvas canvas, BufferedImage bg, int x, int y)
    {
        this.bg = bg;
        this.x = x;
        this.y = y;
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
     * @param int delta-x
     * @param int delta-y
     */
    public void update(int dx, int dy)
    {
        this.x += dx;
        this.y += dy;
    }
    /**
     * Update the env with entites.
     * Useful for motion in POV of player.
     * @param int delta-x
     * @param int delta-y
     * @param boolean movEntities
     */
    public void update(int dx, int dy, boolean movEntities)
    {
        this.x += dx;
        this.y += dy;
        if (movEntities)
            for (Entity e : this.entities)
                e.update(dx, dy);
    }
    /**
     * Update the env by absolute position.
     * @param int new-x
     * @param int new-y
     */
    public void move(int x, int y)
    {
        int dx = x - this.x;
        int dy = y - this.y;
        this.update(dx, dy);
    }
    /**
     * Update the env with enties by absolute position.
     * @param int new-x
     * @param int new-y
     * @param boolean movEntities
     */
    public void move(int x, int y, boolean movEntities)
    {
        int dx = x - this.x;
        int dy = y - this.y;
        this.update(dx, dy, true);
    }
    /**
     * Render env with the entities.
     */
    public void render()
    {
        Graphics gfx = this.bs.getDrawGraphics();
        gfx.clearRect(0, 0, this.width, this.height);
        gfx.drawImage(this.bg, this.x, this.y, null);
        if (this.entities != null)
            for (Entity e : this.entities)
                e.render(gfx);
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
