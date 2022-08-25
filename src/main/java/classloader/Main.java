package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CustomClassLoader customClassLoader = new CustomClassLoader("/home/zhao/lib");

        // 加载类
        Class<?> loadClass = customClassLoader.loadClass("classloader.ext.Test");

        Object instance = loadClass.getDeclaredConstructor().newInstance();
        Method method = loadClass.getMethod("classloader");
        method.invoke(instance);
        System.out.println(loadClass.getClassLoader().toString());
    }

}
