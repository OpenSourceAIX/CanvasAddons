package cn.colintree.aix.CanvasAddons.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {

    private static Throwable lastError;

    public static Throwable getLastError() {
        return lastError;
    }
    
    public static Object getField(Object obj, String name) {
        return getField(obj.getClass(), name, obj);
    }
    
    public static Object getField(Class<?> claz, String name, Object obj) {
        try {
            Field f = claz.getDeclaredField(name);
            f.setAccessible(true);
            return f.get(obj);
        } catch (NoSuchFieldException e) {
            lastError = e;
        } catch (IllegalAccessException e) {
            lastError = e;
        }
        return null;
    }

    public static Object invokeMethod(Object obj, String name, Class<?>[] argClass, Object[] arg) {
        return invokeMethod(obj.getClass(), name, argClass, arg, obj);
    }
    
    public static Object invokeMethod(Class<?> claz, String name, Class<?>[] argClass, Object[] arg, Object obj) {
        try {
            Method method = claz.getDeclaredMethod(name, argClass);
            method.setAccessible(true);
            return method.invoke(obj, arg);
        } catch (NoSuchMethodException e) {
            lastError = e;
        } catch (IllegalAccessException e) {
            lastError = e;
        } catch (InvocationTargetException e) {
            lastError = e;
        }
        return null;
    }

}