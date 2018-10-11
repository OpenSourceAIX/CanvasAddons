package cn.colintree.aix.CanvasAddons;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.Ball;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.ImageSprite;
import com.google.appinventor.components.runtime.Sprite;

@DesignerComponent(version = CanvasAddons.VERSION,
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
    public void AA_PlaceHolder(Sprite placeHolder) {
    	super.AA_PlaceHolder(placeHolder);
    }

    @Override
    protected void inheritProperties(Sprite sprite) {
    	if (sprite instanceof Ball) {
            Ball ball = (Ball) sprite;
            Enabled(ball.Enabled());
            Heading(ball.Heading());
            Interval(ball.Interval());
            Height(ball.Height());
            Width(ball.Width());
            Speed(ball.Speed());
            Visible(ball.Visible());
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

            @Override
            protected void postEvent(final Sprite sprite, final String eventName, final Object... args) {
                if (eventName.equals("CollidedWith")) {
                    CircleImageSprite.this.CollidedWith((Sprite)args[0]);
                } else if (eventName.equals("EdgeReached")) {
                    CircleImageSprite.this.EdgeReached((Integer)args[0]);
                } else if (eventName.equals("NoLongerCollidingWith")) {
                    CircleImageSprite.this.NoLongerCollidingWith((Sprite)args[0]);
                }
            }

            @Override
            public void Dragged(float startX, float startY, float prevX, float prevY, float currentX, float currentY) {
                CircleImageSprite.this.Dragged(startX, startY, prevX, prevY, currentX, currentY);
            }
            @Override
            public void Touched(float x, float y) {
                CircleImageSprite.this.Touched(x, y);
            }
            @Override
            public void Flung(float x, float y, float speed, float heading, float xvel, float yvel) {
                CircleImageSprite.this.Flung(x, y, speed, heading, xvel, yvel);
            }
            @Override
            public void TouchDown(float x, float y) {
                CircleImageSprite.this.TouchDown(x, y);
            }
            @Override
            public void TouchUp(float x, float y) {
                CircleImageSprite.this.TouchUp(x, y);
            }
        };
    }


    @Override
    @SimpleEvent
    public void CollidedWith(Sprite other) {
        postEvent("CollidedWith", other);
    }
    @Override
    @SimpleEvent
    public void Dragged(float startX, float startY, float prevX, float prevY, float currentX, float currentY) {
        postEvent("Dragged", startX, startY, prevX, prevY, currentX, currentY);
    }
    @Override
    @SimpleEvent
    public void EdgeReached(int edge) {
        postEvent("EdgeReached", edge);
    }
    @Override
    @SimpleEvent
    public void NoLongerCollidingWith(Sprite other) {
        postEvent("NoLongerCollidingWith", other);
    }
    @Override
    @SimpleEvent
    public void Touched(float x, float y) {
        postEvent("Touched", x, y);
    }
    @Override
    @SimpleEvent
    public void Flung(float x, float y, float speed, float heading, float xvel, float yvel) {
        postEvent("Flung", x, y, speed, heading, xvel, yvel);
    }
    @Override
    @SimpleEvent
    public void TouchDown(float x, float y) {
        postEvent("TouchDown", x, y);
    }
    @Override
    @SimpleEvent
    public void TouchUp(float x, float y) {
        postEvent("TouchUp", x, y);
    }


    @DesignerProperty(
        editorType = PropertyTypeConstants.PROPERTY_TYPE_ASSET,
        defaultValue = "")
    @SimpleProperty
    public void Picture(String path) {
        super.Picture(path);
    }

}