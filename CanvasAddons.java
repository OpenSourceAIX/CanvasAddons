package cn.colintree.aix.CanvasAddons;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Canvas;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.util.YailList;

import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import cn.colintree.aix.CanvasAddons.util.ReflectUtil;

@DesignerComponent(
    category = ComponentCategory.EXTENSION,
    iconName = "aiwebres/icon.png",
    version = CanvasAddons.VERSION,
    nonVisible = true)
@SimpleObject(external = true)
public class CanvasAddons extends AndroidNonvisibleComponent {

    public static final int VERSION = 1;

    private final Form form;
    private Canvas canvas;
    private View view;
    private Paint paint;
    
    public CanvasAddons(ComponentContainer container) {
        super(container.$form());

        this.form = container.$form();
    }

    @DesignerProperty(
        editorType = PropertyTypeConstants.PROPERTY_TYPE_COMPONENT + ":com.google.appinventor.components.runtime.Canvas")
    @SimpleProperty(
        category = PropertyCategory.BEHAVIOR)
    public void Canvas(Canvas canvas) {
        this.canvas = canvas;
        view = canvas.getView();
        paint = (Paint) ReflectUtil.getField(canvas, "paint");
    }

    @SimpleProperty
    public Canvas Canvas() {
        return canvas;
    }

    private android.graphics.Canvas getCanvas() {
        return (android.graphics.Canvas) ReflectUtil.getField(view, "canvas");
    }

    @SimpleProperty
    public boolean BindedCanvas() {
        return canvas != null;
    }

    /**
     * Draws a shape on the canvas, 
     * point list should be a list contains sub-lists with two number which represents a coordinate
     *
     * @param pointList  a list of points, should contains sub-lists with two number which represents a coordinate
     * @param fill  true for filled shape; false for shape outline
     */
    @SimpleFunction
    public void DrawShape(YailList pointList, boolean fill) {
        if (getCanvas() == null) {
            return;
        }
        Path path;
        try {
            path = parsePath(parsePointList(pointList));
        } catch (IllegalArgumentException e) {
            path = null;
        }
        if (path == null) {
            return;
        }
        path.close();
        Paint p = new Paint(paint);
        p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
        getCanvas().drawPath(path, p);
        view.invalidate();
    }

    private Path parsePath(float[][] points) throws IllegalArgumentException {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        float scalingFactor = form.deviceDensity();

        Path path = new Path();
        path.moveTo(points[0][0] * scalingFactor, points[0][1] * scalingFactor);
        for (int i = 1; i < points.length; i++) {
            path.lineTo(points[i][0] * scalingFactor, points[i][1] * scalingFactor);
        }

        return path;
    }

    private float[][] parsePointList(YailList pointList) throws IllegalArgumentException {
        if (pointList == null) {
            throw new IllegalArgumentException();
        }
        float[][] points = new float[pointList.size()][2];
        int index = 0;
        YailList pointYailList;
        for (Object pointObject : pointList.toArray()) {
            if (pointObject instanceof YailList) {
                pointYailList = (YailList) pointObject;
                if (pointYailList.size() == 2) {
                    try {
                        points[index][0] = Float.parseFloat(pointYailList.getString(0));
                        points[index][1] = Float.parseFloat(pointYailList.getString(1));
                        index++;
                    } catch (NullPointerException e) {
                        throw new IllegalArgumentException(e.fillInStackTrace());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(e.fillInStackTrace());
                    }
                } else {
                    throw new IllegalArgumentException("length of item YailList("+ index +") is not 2");
                }
            } else {
                throw new IllegalArgumentException("item("+ index +") in YailList is not a YailList");
            }
        }
        return points;
    }

    /**
     * Draw an arc on Canvas, by drawing a arc from a specified oval
     *   (specified by left, top, right & bottom),
     * start angle is 0 when heading to the right, and increase when rotate clockwise.
     * 
     * @param left   the left end of the oval
     * @param top    the top of the oval
     * @param right  the right end of the oval
     * @param bottom the bottom of the oval
     * @param startAngle the start angle of the arc, rightward as 0, clockwise as positive, in degree
     * @param sweepAngle the sweep angle of the arc, clockwise as positive, in degree
     * @param useCenter  fill a sector instead of just a arc if arc (when true)
     * @param fill  true for filled arc (or sector); false for arc outline
     */
    @SimpleFunction
    public void DrawArc(int left, int top, int right, int bottom, 
    float startAngle, float sweepAngle, boolean useCenter, boolean fill) {
        if (getCanvas() == null) {
            return;
        }
        float scalingFactor = form.deviceDensity();
        Paint p = new Paint(paint);
        p.setStyle(fill ? Paint.Style.FILL : Paint.Style.STROKE);
        getCanvas().drawArc(
            scalingFactor * left, scalingFactor * top,
            scalingFactor * right, scalingFactor * bottom,
            startAngle, sweepAngle, useCenter, p);
        view.invalidate();
    }

}