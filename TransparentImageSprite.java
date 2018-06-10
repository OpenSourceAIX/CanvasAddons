package cn.colintree.aix.CanvasAddons;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.ImageSprite;
import com.google.appinventor.components.runtime.Sprite;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import cn.colintree.aix.CanvasAddons.util.ColorUtil;
import cn.colintree.aix.CanvasAddons.util.ReflectUtil;

@DesignerComponent(version = YaVersion.IMAGESPRITE_COMPONENT_VERSION,
    description = "",
    category = ComponentCategory.EXTENSION,
    nonVisible = true)
@SimpleObject(external = true)
public final class TransparentImageSprite extends AbstractImageSprite {

    public static final int DEFAULT_IGNORE_COLOR = COLOR_NONE;
    public static final int DEFAULT_TOLERANCE = 10;

    private int ignoreColor;
    private int tolerance;

    public TransparentImageSprite(ComponentContainer container) {
        super(container);

        IgnoreColor(DEFAULT_IGNORE_COLOR);
        IgnoreTolerance(DEFAULT_TOLERANCE);
    }

    @Override
    @DesignerProperty(
        editorType = PropertyTypeConstants.PROPERTY_TYPE_COMPONENT + ":com.google.appinventor.components.runtime.ImageSprite")
    @SimpleProperty(userVisible = false)
    public void A_PlaceHolder(Sprite placeHolder) {
        super.A_PlaceHolder(placeHolder);
    }

    @Override
    protected void inheritProperties(Sprite sprite) {
        if (sprite instanceof ImageSprite) {
            ImageSprite imageSprite = (ImageSprite) sprite;
            Heading(imageSprite.Heading());
            Interval(imageSprite.Interval());
            Height(imageSprite.Height());
            Width(imageSprite.Width());
            Picture(imageSprite.Picture());
            Rotates(imageSprite.Rotates());
            Speed(imageSprite.Speed());
            X(imageSprite.X());
            Y(imageSprite.Y());
            Z(imageSprite.Z());
        }
    }

    @Override
    protected void createSprite() {
        sprite = new ImageSprite(canvas) {
            @Override
            public boolean containsPoint(double canvasX, double canvasY) {
                if (!super.containsPoint(canvasX, canvasY)) {
                    return false;
                }
                double componentX = canvasX - xLeft + 1;
                double componentY = canvasY - yTop + 1;
                BitmapDrawable bd = (BitmapDrawable) ReflectUtil.getField(ImageSprite.class, "drawable", sprite);
                Bitmap b = bd.getBitmap();
                int bitmapX = (int)(componentX * b.getWidth() / Width());
                int bitmapY = (int)(componentY * b.getHeight() / Height());

                int pixel = b.getPixel(bitmapX - 1, bitmapY - 1);
                int t = ColorUtil.tolerance(pixel, ignoreColor);
                return t > tolerance;
            }
        };
    }

    @DesignerProperty(
        editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
        defaultValue = DEFAULT_VALUE_COLOR_NONE)
    @SimpleProperty
    public void IgnoreColor(int rgba) {
        ignoreColor = rgba;
    }

    @SimpleProperty
    public int IgnoreColor() {
        return ignoreColor;
    }

    @DesignerProperty(
        editorType = PropertyTypeConstants.PROPERTY_TYPE_INTEGER,
        defaultValue = "" + DEFAULT_TOLERANCE)
    @SimpleProperty
    public void IgnoreTolerance(int tolerance) {
        this.tolerance = tolerance;
    }

    @SimpleProperty
    public int Tolerance() {
        return tolerance;
    }

}