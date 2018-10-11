package cn.colintree.aix.CanvasAddons;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Sprite;

import android.os.Handler;
import cn.colintree.aix.CanvasAddons.util.ReflectUtil;

@SimpleObject(external = true)
public abstract class AbstractSprite<T extends Sprite> extends AndroidNonvisibleComponent {

    protected final ComponentContainer container;

    protected T sprite;
    protected Canvas canvas;
    protected Handler androidUIHandler;

    public AbstractSprite(ComponentContainer container) {
        super(container.$form());
        this.container = container;
        androidUIHandler = new Handler();
    }
    
    /**
     * A place holder for the sprite.
     * 
     * The property name "AA_PlaceHolder" is in purpose of making this property
     * at the top of the property list, and being executed first before other properties are handled
     * 
     * @param placeHolder
     */
    @SimpleProperty(
        category = PropertyCategory.BEHAVIOR,
        userVisible = false)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COMPONENT)
    public void AA_PlaceHolder(Sprite placeHolder) {
        canvas = (Canvas)ReflectUtil.getField(Sprite.class, "canvas", placeHolder);

        if (sprite == null) {
            createSprite();
            sprite.Initialize();
        }

        inheritProperties(placeHolder);
        placeHolder.onDelete();
    }

    /**
     * Creates a sprite and store it into field 'sprite', 
     * after this method is executed, field 'sprite' should contains a non-null value
     */
    protected abstract void createSprite();

    /**
     * Handle property inherit
     */
    protected abstract void inheritProperties(Sprite sprite);

    /**
     * Return the sprite that is used in the component
     */
    @SimpleProperty
    public Sprite Sprite() {
        return sprite;
    }

    protected void postEvent(final String eventName, final Object... args) {
        androidUIHandler.post(new Runnable() {
            public void run() {
                EventDispatcher.dispatchEvent(AbstractSprite.this, eventName, args);
            }
        });
    }

    @SimpleFunction(description = "Makes this sprite bounce, as if off a wall.  " +
        "For normal bouncing, the edge argument should be the one returned by EdgeReached.")
    public void Bounce (int edge) {
        sprite.Bounce(edge);
    }

    @SimpleFunction
    public boolean CollidingWith(Sprite other) {
        return sprite.CollidingWith(other);
    }

    @SimpleFunction
    public void MoveIntoBounds() {
        sprite.MoveIntoBounds();
    }
    
    @SimpleFunction(
        description = "Moves the sprite so that its left top corner is at " +
        "the specfied x and y coordinates.")
    public void MoveTo(double x, double y) {
        sprite.MoveTo(x, y);
    }

    @SimpleFunction(
        description = "Turns the sprite to point towards the point " +
        "with coordinates as (x, y).")
    public void PointInDirection(double x, double y) {
        sprite.PointInDirection(x, y);
    }

    @SimpleFunction(
        description = "Turns the sprite to point towards a designated " +
        "target sprite. The new heading will be parallel to the line joining " +
        "the centerpoints of the two sprites.")
    public void PointTowards(Sprite target) {
        sprite.PointTowards(target);
    }

}