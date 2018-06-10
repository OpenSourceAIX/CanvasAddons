package cn.colintree.aix.CanvasAddons;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.Ball;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.ImageSprite;
import com.google.appinventor.components.runtime.Sprite;

@DesignerComponent(version = YaVersion.IMAGESPRITE_COMPONENT_VERSION,
    description = "",
    category = ComponentCategory.EXTENSION,
    nonVisible = true)
@SimpleObject(external = true)
public final class CircleImageSprite extends AbstractImageSprite {

    public CircleImageSprite(ComponentContainer container) {
        super(container);
    }

    @Override
    @DesignerProperty(
        editorType = PropertyTypeConstants.PROPERTY_TYPE_COMPONENT + ":com.google.appinventor.components.runtime.Ball")
    @SimpleProperty(userVisible = false)
    public void A_PlaceHolder(Sprite placeHolder) {
    	super.A_PlaceHolder(placeHolder);
    }

    @Override
    protected void inheritProperties(Sprite sprite) {
    	if (sprite instanceof Ball) {
            Ball ball = (Ball) sprite;
            Heading(ball.Heading());
            Interval(ball.Interval());
            Height(ball.Height());
            Width(ball.Width());
            Speed(ball.Speed());
            X(ball.X());
            Y(ball.Y());
            Z(ball.Z());
        }
    }
    
    @Override
    protected void createSprite() {
        sprite = new ImageSprite(canvas){
            @Override
            public boolean containsPoint(double qx, double qy) {
                double radius = Width() / 2;
                double centerX = xLeft + radius;
                double centerY = yTop + radius;
                return Math.hypot(qx - centerX, qy - centerY) <= radius;
            }
        };
    }

}