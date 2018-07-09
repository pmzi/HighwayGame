package sample.Helpers;

import java.lang.reflect.Method;

/**
 * Created by pmzi on 7/9/2018.
 */
public class ClassHelper {

    public static boolean methodExists(Class clazz, String methodName) {
        boolean result = false;
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void invokeMethod(Object clazz, String methodName) {
        Method method;
        try{
            method = clazz.getClass().getMethod(methodName);
            method.invoke(methodName);
        }catch (Exception e){

        }
    }

}
