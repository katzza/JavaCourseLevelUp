package reflection.homework;

import java.lang.reflect.InvocationTargetException;

/**
 * ошибка закомменчена в классе Dog
 *  // @RandomInt(min = 1, max = 20)
 *     private String name;
 */
public class RefHomeApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ScanningFiles s = new ScanningFiles();
        String targetPackageName = "ru\\levelup\\lessons";
        s.scanProjectAndCreateClass(targetPackageName);
    }
}



