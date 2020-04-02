package reflection.homework;

import java.io.File;
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

    public void createObjects(ArrayList<String> javaClasses, ArrayList<Class> javaObjects) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String javaClass : javaClasses) {
            Class claz = (Class) Class.forName(javaClass).newInstance();
            javaObjects.add(claz);
        }
    }
}