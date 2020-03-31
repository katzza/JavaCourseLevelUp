package reflection.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProfilingAnnotationProcessor {
    public static Object process (Object object){
       Class<?> objClass = object.getClass();
       Method [] methods = objClass.getDeclaredMethods();
       boolean hasPrAnn =false;
       for(Method method : methods){
           Profiling annotation = method.getAnnotation(Profiling.class);
           if(annotation!=null){
               hasPrAnn = true;
               break;
           }
if (hasPrAnn)
//create dynamic proxy
{
   /* return Proxy.newProxyInstance(
            objClass.getClassLoader(),
            objClass.getInterfaces(),
           // getInvocationHandler(););*/
}
       }return object;
    }
  /*static  class PrIHandler implements InvocationHandler {
private Object target;
/*public ProfilingInvocationHandler (Object target)
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            this.target = target;
            long start = System.nanoTime();
            Object result = method.invoke(target, args);
            return result;
        }
    }*/
}
//DynamicProxy  //помещаем класс в какой-то другой класс, который мы при этом нне создаём