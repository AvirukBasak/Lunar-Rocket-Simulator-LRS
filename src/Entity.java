import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class Entity
{
    private double x, y;
    private double xBound, yBound;
    private BufferedImage entityImg;
    private List<Entity> attachments;
    private Entity parent;
    private boolean hidden;

    protected Environment env;

    /**
     * A soft entity cannot interact with any other entity.
     * Comes into play during collision detection.
     */
    protected boolean isSoft;

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
        this.parent = null;
        this.env = null;
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
        } catch(Exception ex) {
            e.printStackTrace();
        }
    }
    /**
     * Flush entity resources along with its attachments
     */
    public void flush()
    {
        if (this.parent != null && this.parent.attachments != null) this.parent.attachments.remove(this);
        if (this.env != null && this.env.entities != null) this.env.entities.remove(this);
        if (this.entityImg != null) this.entityImg.flush();
        this.entityImg = null;
        if (this.attachments != null)
            for (Entity e : this.attachments)
                e.flush();
        this.attachments = null;
    }
}
