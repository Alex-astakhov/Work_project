package ashotLib;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex Astakhov on 18.12.2016.
 */
class FileIO {

    void createDirectory(String path){
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
    }

    void deleteDirectory(String path){
        File file = new File(path);
        try {
            FileUtils.forceDelete(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean directoryExists(String path){
        File file = new File(path);
        return file.exists() && file.isDirectory();
    }

    boolean directoryIsEmpty(String path){
        File file = new File(path);
        System.out.println(file.listFiles().length);
        return file.listFiles().length == 0;
    }

}
