package group.sample.advanced.rrk.com.advancedsamplegroupapplication.util;

import java.lang.reflect.Field;

/**
 * Created by RyoRyeong Kim on 2017-12-07.
 */

public class ReflectionUtils {

    static Field getField( Class clazz, String fieldName){
        try {
            final Field f = clazz.getDeclaredField(fieldName);
            f.setAccessible(true);
            return f;
        } catch (NoSuchFieldException ignored) {
        }
        return null;
    }

    static Object getValue(Field field,Object obj ){
        try {
            return field.get(obj);
        } catch (IllegalAccessException ignored) {
        }
        return null;
    }
}
