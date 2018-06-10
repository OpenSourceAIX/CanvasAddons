package cn.colintree.aix.CanvasAddons.util;

import android.graphics.Color;

public class ColorUtil {

    public static int tolerance(int color1, int color2) {
        // transparent colors looks the same
        if (Color.alpha(color1)==0 && Color.alpha(color2)==0) {
            return 0;
        }
        int R = Math.abs(Color.red(color1) - Color.red(color2));
        int G = Math.abs(Color.green(color1) - Color.green(color2));
        int B = Math.abs(Color.blue(color1) - Color.blue(color2));
        return Math.max(R, Math.max(G, B));
    }

    public static String toString(int color) {
        return "rgba("+Color.red(color)+","+Color.green(color)+","+Color.blue(color)+","+Color.alpha(color)+")";
    }

}