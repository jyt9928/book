package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public boolean demo11(Object x){
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
    public static void main(String[] args) {
       Person wo=new Person("李四");
       System.out.println(wo.suohua());
       Person ni=new Person();
       ni.applyName("张三");
       System.out.println(ni.suohua());
       int[][] a=new int[1][1];
       String B[]=new String[]{"wo"};
        Date date=new Date();
        System.out.println(date.toString());
        float x=max(1,2);
        Function zc=new Function();
        zc.sis(123);
    }
    public static int max(int a,int b){
        int max=0;
        if (a > b) {
            max=a;
        }else {
            max=b;
        }
        return max;
    }
    public static float max(float a,float b){
        float max=0;
        if (a > b) {
            max=a;
        }else {
            max=b;
        }
        return max;
    }


}