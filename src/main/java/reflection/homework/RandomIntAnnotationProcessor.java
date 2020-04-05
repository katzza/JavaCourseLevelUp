package reflection.homework;

import net.bytebuddy.implementation.bytecode.Throw;
import reflection.homework.RandomInt;

import java.lang.reflect.Field;
import java.util.Random;

import static java.lang.StrictMath.abs;

public class RandomIntAnnotationProcessor {

    public static Object process(Object object) throws IllegalAccessException, InvalidTypeException {
        Class<?> objClass = object.getClass();

        Field[] fields = objClass.getDeclaredFields();   //достали все поля
        for (Field f : fields) {
            RandomInt annotation = f.getAnnotation(RandomInt.class);
            if (annotation != null) {
                if (!f.getType().toString().equals("int")) {
                    throw new InvalidTypeException("ERROR! The annotated field is not int");
                }
                int max = annotation.max();
                int min = annotation.min();
                Random random = new Random();
                int number = min + random.nextInt(max - min);
                f.setAccessible(true);
                f.set(object, number);
            }
        }
        return object;
    }

}
