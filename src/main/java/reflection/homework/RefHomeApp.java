package reflection.homework;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RefHomeApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    //    File file1 = new File("RefHomeApp");
   //    String sss = file1.getAbsolutePath().toString() ;


        String pathName = "C:\\Users\\emokeeva\\IdeaProjects\\pr2\\src\\main\\java\\ru\\levelup\\lessons";
        ArrayList<String> files = new ArrayList<>();
        ScanningFiles s = new ScanningFiles();
        s.processFilesFromFolderS(pathName, files);
        ArrayList<String> javaClasses = new ArrayList<>();
        for (String file : files
        ) {
            int lastSlash = file.lastIndexOf("\\");
            javaClasses.add(file.substring(lastSlash + 1));
        }
        ArrayList<Class> javaObjects = null;
        s.createObjects (javaClasses, javaObjects);
        int i = 0;


    }



    }




