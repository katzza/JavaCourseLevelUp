package reflection.homework;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ScanningFiles {
    public void scanProjectAndCreateClass(String targetPackageName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //сканируем проект и добываем файлы с расширением .java из целефой папки
        String path = System.getProperty("user.dir");  //сам проект
        File folder = new File(path);
        ArrayList<String> files = new ArrayList<>();
        processFilesFromFolder(folder, targetPackageName, files); //рекурсивно
        //создаём объекты классов через reflection
        ArrayList<String> javaClasses = new ArrayList<>();
        for (String file : files
        ) {
            int substrIndex = file.lastIndexOf("\\ru\\levelup");
            javaClasses.add(file.substring(substrIndex + 1, file.length() - 5).replace('\\', '.'));
        }
        createObjects(javaClasses);
    }

    private void processFilesFromFolder(File folder, String targetPackageName, ArrayList<String> files) {
        File[] folderEntries = folder.listFiles();
        assert folderEntries != null;
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry, targetPackageName, files);
                continue;
            }
            // иначе вам попался файл, обрабатывайте его!
            if (entry.toString().contains(targetPackageName)
                    && entry.toString().length() - entry.toString().lastIndexOf(".java") == 5)
                files.add(entry.toString());
        }
    }

    private void createObjects(ArrayList<String> javaClasses) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //создали объект  Class
        ArrayList<Class> javaObjects = new ArrayList<>();
        for (String javaClass : javaClasses) {
            Class<?> claz = Class.forName(javaClass);
            javaObjects.add(claz);
        }
        //создали объекты
        for (var claz : javaObjects
        ) {
            Object o = claz.getDeclaredConstructor().newInstance();
            try {
                RandomIntAnnotationProcessor.process(o);
            } catch (InvalidTypeException e) {
                System.exit(0);
            }
            System.out.println(o.toString());
        }
    }


}
