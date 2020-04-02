package reflection;

import reflection.annotation.RandomStringAnnotationProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionApp {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        //Region Cars
        Car carObj = new Car("Rio");
        Class<?> carClass = carObj.getClass();
        // LIKE Class<?> carClass1 = Car.class;   //одинаково
        carClass.getFields();

        Field[] publicFields = carClass.getFields();
        for (Field publicField : publicFields) {
            System.out.println("Public Fields" + publicField.getName());
        }
        Field modelField = carClass.getDeclaredField("model");
        System.out.println(modelField.getName());
        modelField.setAccessible(true);  //можем залезать в приватные поля
        modelField.set(carObj, "Solaris");
        System.out.println(carObj.getModel());


        Method changeModelM = carClass.getDeclaredMethod("changeModel", String.class);
        changeModelM.setAccessible(true);
        // LIKE carObj.changeModel("Polo");
        changeModelM.invoke(carObj, "Polo");

        Constructor<?> carConstructor = carClass.getDeclaredConstructor(String.class, String.class);
        carConstructor.setAccessible(true);

        Car refcar = (Car) carConstructor.newInstance("l", "G");
        //newInstance выполнение конструктора
        System.out.println(refcar.getBrand() + " " + refcar.getModel());

        Car car = new Car("Polo");
        RandomStringAnnotationProcessor.process(car);
        System.out.println(car.getBrand() + " " + car.getModel());
        //End of region Cars

    }
}
