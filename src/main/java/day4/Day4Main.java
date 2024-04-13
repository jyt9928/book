package day4;

import java.io.*;
import java.util.ArrayList;

public class Day4Main {
    public static void main(String[] args) throws IOException {
        ArrayList<String>pathArrayList=new ArrayList<>();
        File path=new File("C:\\Users\\Administrator\\IdeaProjects\\demo\\src\\main\\java\\day4\\path.txt");
        FileWriter xPath=new FileWriter(path);
        xPath.write("qwe\n");
        xPath.write("zxcvbnm");
        xPath.flush();
        xPath.close();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line=br.readLine())!=null) {
            System.out.println(line);
        }
        br.close();
    }
}
