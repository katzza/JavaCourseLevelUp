package reflection.homework;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ScanningFiles {
    public void processFilesFromFolderS(String pathName, ArrayList<String> files) {
        File folder = new File(pathName);
        processFilesFromFolder(folder, files);
    }

    public void processFilesFromFolder(File folder, ArrayList<String> files) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry, files);
                continue;
            }
            // иначе вам попался файл, обрабатывайте его!
            files.add(entry.toString());
        }
    }

    public void createObjects(ArrayList<String> javaClasses) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
  //создали объект  Class
        ArrayList<Class> javaObjects = new ArrayList<Class>();
        for (String javaClass : javaClasses) {
            Class claz = Class.forName(javaClass);
            javaObjects.add(claz);
        }
        //создали объекты
        for (Class claz:javaObjects
             ) {
            Object o = claz.getDeclaredConstructor().newInstance();
            System.out.println(o.toString());
        }
    }
}