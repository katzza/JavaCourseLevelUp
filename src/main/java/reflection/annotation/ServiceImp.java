package reflection.annotation;

public class ServiceImp implements Service {
    @Override
    @Profiling
    public void doSomething() {
        System.out.println("do smf");
    }
}
