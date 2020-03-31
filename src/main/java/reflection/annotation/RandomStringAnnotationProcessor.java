package reflection.annotation;

import java.lang.reflect.Field;
import java.util.Random;

public class RandomStringAnnotationProcessor {
    public static Object process(Object object) throws IllegalAccessException {
        Class<?> objClass = object.getClass();

        Field[] fields = objClass.getDeclaredFields();   //достали все поля
        for (Field f : fields) {
            RandomString annotation = f.getAnnotation(RandomString.class);
            if (annotation != null) {
                int max = annotation.maxLength();
                Random random = new Random();
                int number = random.nextInt(Brands.values().length);
                String generDr = Brands.values()[number].name().toLowerCase();
                String brand = generDr.substring(0, Math.min(generDr.length(), max));
                f.setAccessible(true);
                f.set(object, brand);
            }
        }
        return object;
    }
}
