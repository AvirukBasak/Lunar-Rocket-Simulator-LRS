import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class Entity
{
    public double x, y;
    public double xBound, yBound;
    protected BufferedImage entityImg;
    protected List<Entity> attachments;

    protected Environment env;
    protected Entity parent;

    /**
     * A soft entity cannot interact with any other entity.
     * Comes into play during collision detection.
     */
    protected boolean isSoft;

    protected boolean hidden;

    /**
     * An entity is an existence, visualised by its image attribute.
     * @param BufferedImage The attribute image
     * @param double x coordinate entity
     * @param double y coordinate entity
     */
    public Entity(double x, double y, BufferedImage img)
    {
        this.isSoft = false;
        this.hidden = false;
        this.x = x;
        this.y = y;
        if (img != null) this.entityImg = img;
        else throw new NullPointerException("Entity: image is null");
        this.xBound = img.getWidth();
        this.yBound = img.getHeight();
        this.attachments = null;
    }
    public void hide() { this.hidden = true; }
    public void show() { this.hidden = false; }
    /**
     * Updates posn of an entity along with its attachments.
     * @param double delta-x
     * @param double delta-y
     */
    public void update(double dx, double dy)
    {
        this.x += dx;
        this.y += dy;
        if (this.attachments != null)
            for (Entity e : this.attachments)
                e.update(dx, dy);
    }
    /**
     * Moves an entity to absolute posn along with its attachments.
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
     * Draw the entity along with its attachments.
     * @param Graphics the graphics object used for drawing
     */
    public void render(Graphics gfx)
    {
        if (hidden) return;
        gfx.drawImage(this.entityImg, (int) this.x, (int) this.y, null);
        if (this.attachments != null)
            for (Entity e : this.attachments)
                e.render(gfx);
    }
    /**
     * Attatch multiple entities to create a larger compound.
     * Do NOTE that newly attatched entities get drawn over older ones.
     * Also, the parent entity is drawn before its attachments.
     */
    public void attatch(Entity e)
    {
        e.parent = this;
        if (this.attachments == null)
            this.attachments = new ArrayList<Entity>();
        this.attachments.add(e);
    }
    /**
     * Dettatch entity.
     */
    public void dettatch(Entity e)
    {
        if (this.attachments != null) try {
            this.attachments.remove(e);
        } catch(Exception ex) {}
    }
    /**
     * Flush entity resources along with its attachments
     */
    public void flush()
    {
        this.parent.attachments.remove(this);
        this.env.entities.remove(this);
        this.entityImg.flush();
        this.entityImg = null;
        if (this.attachments != null)
            for (Entity e : this.attachments)
                e.flush();
        this.attachments = null;
    }
}
