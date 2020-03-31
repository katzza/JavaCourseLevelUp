package reflection.annotation;

public class ServiceFactory {
    public static Service getService()
    {
        return (Service) ProfilingAnnotationProcessor.process(new ServiceImp());
    }
}
