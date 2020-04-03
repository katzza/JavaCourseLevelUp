package reflection.homework;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RefHomeApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

//String path = System.getProperty("user.dir");
        String pathName = "C:\\Users\\emokeeva\\IdeaProjects\\pr2\\src\\main\\java\\ru\\levelup\\lessons";
        ArrayList<String> files = new ArrayList<>();
        ScanningFiles s = new ScanningFiles();
        s.processFilesFromFolderS(pathName, files);
        ArrayList<String> javaClasses = new ArrayList<>();
        for (String file : files
        ) {
            int substrIndex = file.lastIndexOf("\\ru\\levelup");
            javaClasses.add(file.substring(substrIndex + 1, file.length()-5).replace('\\', '.'));
        }
        s.createObjects (javaClasses);
     }
    }




