package cn.colintree.aix.CanvasAddons;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.ImageSprite;
import com.google.appinventor.components.runtime.Sprite;

@SimpleObject(external = true)
public abstract class AbstractImageSprite extends AbstractSprite<ImageSprite> {

    public AbstractImageSprite(ComponentContainer container) {
        super(container);
    }

    // abstract methods for Events
    public abstract void CollidedWith(Sprite other);
    public abstract void Dragged(float startX, float startY, float prevX, float prevY, float currentX, float currentY);
    public abstract void EdgeReached(int edge);
    public abstract void NoLongerCollidingWith(Sprite other);
    public abstract void Touched(float x, float y);
    public abstract void Flung(float x, float y, float speed, float heading, float xvel, float yvel);
    public abstract void TouchDown(float x, float y);
    public abstract void TouchUp(float x, float y);

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
    //    defaultValue = "True")
    @SimpleProperty
    public void Enabled(boolean enabled) {
        sprite.Enabled(enabled);
    }
    @SimpleProperty
    public boolean Enabled() {
        return sprite.Enabled();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT,
    //    defaultValue = "0")
    @SimpleProperty
    public void Heading(double heading) {
        sprite.Heading(heading);
    }
    @SimpleProperty
    public double Heading() {
        return sprite.Heading();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER,
    //    defaultValue = "" + LENGTH_PREFERRED)
    @SimpleProperty
    public void Height(int height) {
        sprite.Height(height);
    }
    @SimpleProperty
    public int Height() {
        return sprite.Height();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER,
    //    defaultValue = "" + LENGTH_PREFERRED)
    @SimpleProperty
    public void Width(int width) {
        sprite.Width(width);
    }
    @SimpleProperty
    public int Width() {
        return sprite.Width();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_NON_NEGATIVE_INTEGER,
    //    defaultValue = "100")
    @SimpleProperty
    public void Interval(int interval) {
        sprite.Interval(interval);
    }
    @SimpleProperty
    public int Interval() {
        return sprite.Interval();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET,
    //    defaultValue = "")
    @SimpleProperty
    public void Picture(String path) {
        sprite.Picture(path);
    }
    @SimpleProperty
    public String Picture() {
        return sprite.Picture();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
    //    defaultValue = "True")
    @SimpleProperty
    public void Rotates(boolean rotates) {
        sprite.Rotates(rotates);
    }
    @SimpleProperty
    public boolean Rotates() {
        return sprite.Rotates();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT,
    //    defaultValue = "0.0")
    @SimpleProperty
    public void Speed(float speed) {
        sprite.Speed(speed);
    }
    @SimpleProperty
    public float Speed() {
        return sprite.Speed();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
    //    defaultValue = "True")
    @SimpleProperty
    public void Visible(boolean visible) {
        sprite.Visible(visible);
    }
    @SimpleProperty
    public boolean Visible() {
        return sprite.Visible();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT,
    //    defaultValue = "0.0")
    @SimpleProperty
    public void X(double x) {
        sprite.X(x);
    }
    @SimpleProperty
    public double X() {
        return sprite.X();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT,
    //    defaultValue = "0.0")
    @SimpleProperty
    public void Y(double y) {
        sprite.Y(y);
    }
    @SimpleProperty
    public double Y() {
        return sprite.Y();
    }

    //@DesignerProperty(
    //    editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT,
    //    defaultValue = "1.0")
    @SimpleProperty
    public void Z(double z) {
        sprite.Z(z);
    }
    @SimpleProperty
    public double Z() {
        return sprite.Z();
    }

}