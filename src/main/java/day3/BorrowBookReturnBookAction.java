
package day3;

import day3.bean.Book;
import day3.bean.Student;
import jdbc.MysqlJdbc;
import org.example.Mammal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;

public class BorrowBookReturnBookAction implements Action {

    /*
     * 借书
     * */
    public void borrowBook() {
        System.out.println("请输入学生学号输入0退出");
        Scanner scanner = new Scanner(System.in);
        int student = Integer.parseInt(scanner.next());
        if (MysqlJdbc.inspectSql("student", "id", student)) {
            System.out.println("请输入要借的书的ISBN");
            Scanner scanner1 = new Scanner(System.in);
            String ISBN = scanner1.next();
            if (MysqlJdbc.inspectSql("book", "bookISBN", ISBN) && !MysqlJdbc.inspectSql("book","residuebook",0)) {
                Map<String, Object> map = new HashMap();
                map.put("studentid", student);
                map.put("bookISBN", ISBN);
                map.put("borrowtime", LocalDateTime.now());
                map.put("returntime", null);
                MysqlJdbc.joinSql("bookstudentaction", map.keySet().toArray(new String[0]), map.values().toArray(new Object[0]));
                int total= (int) MysqlJdbc.single("SELECT totalbook FROM book WHERE bookISBN=?","totalbook",(ISBN));
                int number=MysqlJdbc.number("SELECT COUNT(*) FROM bookstudentaction WHERE returntime is null");
                MysqlJdbc.changeSql("book","borrowbook",ISBN,number,"bookISBN");
                MysqlJdbc.changeSql("book","residuebook",ISBN,(total-number),"bookISBN");
                printMenu();
            } else {
                System.out.println("输入有误或者书库已经无书");
                borrowBook();
            }
        } else if (student==-1){
            printMenu();
        }else {
            System.out.println("输入的学生号有误");
            borrowBook();
        }
    }

    /*
     * 还书
     * */
    public void returnBook() {
        System.out.println("请输入学生学号");
        Scanner scanner=new Scanner(System.in);
        int id=Integer.parseInt(scanner.next());
        System.out.println("请输入还书编号");
        Scanner scanner1 = new Scanner(System.in);
        int id2=Integer.parseInt(scanner1.next());
        Object ISBN1= MysqlJdbc.single("SELECT bookISBN FROM bookstudentaction WHERE id=?","bookISBN",id2);
        long ISBN=(long)ISBN1;
        int total= (int) MysqlJdbc.single("SELECT totalbook FROM book WHERE bookISBN=?","totalbook",ISBN);
        if (MysqlJdbc.inspectSql("student","id",id) && !MysqlJdbc.inspectSql("book","residuebook",total)){
           MysqlJdbc.changeSql("bookstudentaction","returntime",id,LocalDateTime.now(),"studentid");
            int number=MysqlJdbc.number("SELECT COUNT(*) FROM bookstudentaction WHERE returntime is null");
            MysqlJdbc.changeSql("book","borrowbook",ISBN,number,"bookISBN");
            MysqlJdbc.changeSql("book","residuebook",ISBN,(total-number),"bookISBN");
            System.out.println("完成");
           printMenu();
        }else {
            System.out.println("输入错误");
            printMenu();
        }
    }
/*
* 遍历
* */
    public void allData() {
        int x1 = 0;
        int x2 = 10;
        int size = MysqlJdbc.selectSql("SELECT * FROM bookstudentaction").size() / 10;
        if (size == 0) {
            size++;
        }
        for (; true; ) {
            ArrayList<Map<String, Object>> all = MysqlJdbc.allSql("SELECT * FROM bookstudentaction LIMIT ? OFFSET ?", x2, x1);
            for (Map map : all) {
                System.out.println(map);
            }
            if ((x1 / 10) == (size - 1) && x1 == 0) {
                System.out.println("0.退出");
                Scanner op1 = new Scanner(System.in);
                String operation1 = op1.next();
                break;

            } else if (x1 == 0) {
                System.out.println("1.选择跳转到第几页 2.下一页 0.退出 ");
                Scanner op1 = new Scanner(System.in);
                String operation1 = op1.next();
                if (operation1.equals("2")) {
                    x1 = x1 + 10;
                    all.clear();
                } else if (operation1.equals("1")) {
                    System.out.println("请输入要跳转的页数页数不能大于" + (size + 1) + "不能小于1:");
                    Scanner scanner1 = new Scanner(System.in);
                    int page = Integer.parseInt(scanner1.next());
                    if (page <= (size + 1) && page >= 1) {
                        --page;
                        x1 = page * 10;
                    } else {
                        System.out.println("请输入小于" + (size + 1) + "的页数或者大于1");
                    }
                } else {
                    break;
                }
            } else if ((x1 / 10) == size) {
                System.out.println("1.选择跳转到第几页 2.上一页 0.退出 ");
                Scanner op = new Scanner(System.in);
                String operation = op.next();
                if (operation.equals("2")) {
                    x1 = x1 - 10;
                    all.clear();
                } else if (operation.equals("1")) {
                    System.out.println("请输入要跳转的页数页数不能大于" + (size + 1) + "不能小于1:");
                    Scanner scanner1 = new Scanner(System.in);
                    int page = Integer.parseInt(scanner1.next());
                    if (page <= (size + 1) && page >= 1) {
                        --page;
                        x1 = page * 10;
                    } else {
                        System.out.println("请输入小于" + (size + 1) + "的页数或者大于1");
                    }
                } else {
                    break;
                }
            } else {
                System.out.println("1.上一页 2.下一页 0.退出 3.选择跳转到第几页 ");
                Scanner op1 = new Scanner(System.in);
                String operation = op1.next();
                if (operation.equals("1")) {
                    x1 = x1 - 10;
                    all.clear();
                } else if (operation.equals("2")) {
                    x1 = x1 + 10;
                    all.clear();
                } else if (operation.equals("3")) {
                    System.out.println("请输入要跳转的页数页数不能大于" + (size + 1) + "不能小于1:");
                    Scanner scanner1 = new Scanner(System.in);
                    int page = Integer.parseInt(scanner1.next());
                    if (page <= (size + 1) && page >= 1) {
                        --page;
                        x1 = page * 10;
                    } else {
                        System.out.println("请输入小于" + (size + 1) + "的页数或者大于1");
                    }
                } else {
                    break;
                }
            }
        }
        printMenu();
    }
            public void printMenu () {
            System.out.println("1:借书");
            System.out.println("2:还书");
            System.out.println("3.遍历所有数据");
            System.out.println("0:退出");
            Scanner decide = new Scanner(System.in);
            String input = decide.next();
            if (input.equals("1")) {
                borrowBook();
            } else if (input.equals("2")) {
                returnBook();
            }else if (input.equals("3")){
                allData();
            } else {
                Day3.printMenu();
            }
        }
    }
