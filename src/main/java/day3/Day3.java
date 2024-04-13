package day3;

import day3.bean.Book;
import day3.bean.Student;
import jdbc.MysqlJdbc;

import java.util.*;
import java.util.Random;

public class Day3 {
 static HashMap<Integer,Student>STUDENTS=new HashMap<>();
    public static void printMenu() {

        System.out.println("1.学生管理");
        System.out.println("2.书籍管理");
        System.out.println("3:借书还书操作");
        System.out.println("0.退出");
        Scanner scanner= new Scanner(System.in);
        String action=scanner.next();
        if (action.equals("1")){
            new StudentAction().printMenu();
        } else if (action.equals("2")) {
            new BookAction().printMenu();
        } else if (action.equals("3")){
            new BorrowBookReturnBookAction().printMenu();
        }else {

        }
    }
    public static void main(String[] args) {
    printMenu();
    }
//       《追风筝的人》,卡勒德·胡赛尼,人民文学出版社,9787020055957,1,1,0
//        《1984》,乔治·奥威尔,北京十月文艺出版社,9787530205586,1,1,0
//        《百年孤独》,加西亚·马尔克斯,南海出版社,9787544232216,1,1,0
//        《解忧杂货店》,东野圭,吾南海出版社,9787544284319,1,1,0
//        《活着》,余华,作家出版社,9787506317326,1,1,0
            }





