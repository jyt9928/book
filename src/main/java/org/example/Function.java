package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Function {
    public boolean sis(Object x){
        try {
            FileInputStream file = new FileInputStream("E:\\CODE\\CLion\\IJ\\java笔记.txt");
            x = (byte) file.read();
        } catch(FileNotFoundException f) { // Not valid!
            f.printStackTrace();
            return false;
        } catch(IOException i) {
            i.printStackTrace();
            return false;
        }
        return false;
    }
}
