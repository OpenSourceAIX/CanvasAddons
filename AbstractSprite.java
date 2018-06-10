package cn.colintree.aix.CanvasAddons;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.Sprite;

import cn.colintree.aix.CanvasAddons.util.ReflectUtil;

@SimpleObject(external = true)
public abstract class AbstractSprite<T extends Sprite> extends AndroidNonvisibleComponent {

    protected final ComponentContainer container;

    protected T sprite;
    protected Canvas canvas;

    public AbstractSprite(ComponentContainer container) {
        super(container.$form());
        this.container = container;
    }
    
    /**
     * A place holder for the sprite.
     * 
     * @param placeHolder
     */
    @SimpleProperty(
        category = PropertyCategory.BEHAVIOR,
        userVisible = false)
    @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COMPONENT)
    public void A_PlaceHolder(Sprite placeHolder) {
        canvas = (Canvas)ReflectUtil.getField(Sprite.class, "canvas", placeHolder);

        if (sprite == null) {
            createSprite();
            ReflectUtil.invokeMethod(canvas, "addSprite", new Class<?>[] {Sprite.class}, new Object[] {sprite});
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

}