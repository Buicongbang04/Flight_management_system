package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
     public static <T> List<T> readFromBinary(String url){
        List<T> result = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(url))){
            boolean eof = false;
            while(!eof){
                try{
                    T object = (T) ois.readObject();
                    result.add(object);
                }catch (EOFException e){
                    eof = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File path is not exist!");;
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException io) {
            System.out.println("IO Exception: " + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
        return result;
    }
}
