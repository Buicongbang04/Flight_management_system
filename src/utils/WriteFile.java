package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteFile {
    public static <T> void writeToFile(String url, List<T> data){
        try(ObjectOutputStream wof = new ObjectOutputStream(new FileOutputStream(url))){
            for (T object : data) {
                wof.writeObject(object);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
