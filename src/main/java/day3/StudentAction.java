package day3;

import day3.bean.Student;
import jdbc.MysqlJdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentAction implements Action {
    /*
     * 学生的添加
     * */
    public void addStudent() {
        System.out.println("输入新增学生的学号输入0退出");
         Scanner id = new Scanner(System.in);
        String id1 = id.next();
        if (!id1.equals("0")) {
        Map<String, Object> add = new HashMap<>();
        add.put("id", id1);
            System.out.println("请输入新增学生的姓名输入0退出");
            Scanner name=new Scanner(System.in);
            String name1=name.next();
            if (!name1.equals("0")) {
                add.put("name",name1);
            }
        if (!MysqlJdbc.inspectSql("student", "id", id1)) {
            MysqlJdbc.joinSql("student", add.keySet().toArray(new String[0]), add.values().toArray(new Object[0]));
            System.out.println("输入成功");
            printMenu();
        } else {
            System.out.println("已经存在此数据");
            printMenu();
        }
    } else{
            printMenu();
        }
        }
    /*
     * 学生的删除
     * */
    public void delStudent() {
        System.out.println("输入要删除学生的学号.输入0退出");
        Scanner del=new Scanner(System.in);
        String input2=del.next();
        if (!input2.equals("0")){
            if (MysqlJdbc.inspectSql("student","id",input2)){
                MysqlJdbc.delSql("student","id",input2);
                printMenu();
            }else {
                System.out.println("无此学生");
                printMenu();
            }
        }else {
            printMenu();
        }

    }

    /*
     * 学生更改
     * */
    public void changeStudent() {
        System.out.println("请输入要更改学生的学号,输入0退出");
        Scanner set = new Scanner(System.in);
        long set1 = Long.parseLong(set.next());
        if (MysqlJdbc.inspectSql("student","id",set1)){
            if (set1 != 0) {
                System.out.println("选择要修改的参数");
                System.out.println("1.姓名");
                System.out.println("输入0退出");
                System.out.println("如果要修改学号请删除后重新添加");
                Scanner sel = new Scanner(System.in);
                String sel1 = sel.next();
                if (sel1.equals("1")){
                    System.out.println("输入要更改的值");
                    Scanner scanner=new Scanner(System.in);
                    String value=scanner.next();
                    if (MysqlJdbc.changeSql("student","name",set1,value,"id")==0){
                        System.out.println("输入失败");
                        printMenu();
                    }else {
                        System.out.println("输入成功");
                        printMenu();
                    }
                }else{
                    printMenu();
                }
            }else {
                printMenu();
            }
            }else {
            System.out.println("输入有误");
            changeStudent();
        }
        }

    /*
     * 学生遍历
     * */
    public void allStudent() {
        int x1 = 0;
        int x2 = 10;
        int size = MysqlJdbc.selectSql("SELECT * FROM student").size() / 10;
        if (size == 0) {
            size++;
        }
        for (; true; ) {
            ArrayList<Map<String, Object>> all = MysqlJdbc.allSql("SELECT * FROM student LIMIT ? OFFSET ?", x2, x1);
            for (Map map : all) {
                System.out.println(map);
            }
            if ((x1 / 10) == (size-1) && x1 == 0) {
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
                }else {
                    break;
                }
            }
        }
        printMenu();
    }

    /*
     * 学生查询
     * */
    public void getStudent() {
        System.out.println("输入学生姓名或者学号查询,输入0退出");
        Scanner get = new Scanner(System.in);
        String name = get.next();
        int x = 10;
        int x1 = 0;
        if (!name.equals("0")) {
            while (true) {
                ArrayList<Map<String, Object>> select = MysqlJdbc.selectSql("SELECT * FROM student WHERE name LIKE ? OR id like ? LIMIT " + x + " OFFSET " + x1, name, name);
                for (Map map : select) {
                    System.out.println(map);
                }
                int size = select.size()/10;
                if (select == null) {
                    System.out.println("查询失败无此学生");
                    printMenu();
                } else {
                    if ((x1 / 10) == size && x1 == 0) {
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
                            select.clear();
                        } else if (operation1.equals("1")){
                            System.out.println("请输入要跳转的页数页数不能大于"+(size+1)+"不能小于1:");
                            Scanner scanner1=new Scanner(System.in);
                            int page=Integer.parseInt(scanner1.next());
                            if (page<=(size+1) && page>=1){
                                --page;
                                x1=page*10;
                            }else {
                                System.out.println("请输入小于"+(size+1)+"的页数或者大于1");
                            }
                        }else {
                            break;
                        }
                    } else if ((x1 / 10) == size) {
                        System.out.println("1.选择跳转到第几页 2.上一页 0.退出 ");
                        Scanner op = new Scanner(System.in);
                        String operation = op.next();
                        if (operation.equals("2")) {
                            x1 = x1 - 10;
                            select.clear();
                        } else if (operation.equals("1")){
                            System.out.println("请输入要跳转的页数页数不能大于"+(size+1)+"不能小于1:");
                            Scanner scanner1=new Scanner(System.in);
                            int page=Integer.parseInt(scanner1.next());
                            if (page<=(size+1) && page>=1){
                                --page;
                                x1=page*10;
                            }else {
                                System.out.println("请输入小于"+(size+1)+"的页数或者大于1");
                            }
                        }else {
                            break;
                        }
                    } else {
                        System.out.println("1.上一页 2.下一页 0.退出 3.选择跳转到第几页 ");
                        Scanner op1 = new Scanner(System.in);
                        String operation = op1.next();
                        if (operation.equals("1")) {
                            x1 = x1 - 10;
                            select.clear();
                        } else if (operation.equals("2")) {
                            x1 = x1 + 10;
                            select.clear();
                        } else if (operation.equals("3")){
                            System.out.println("请输入要跳转的页数页数不能大于"+(size+1)+"不能小于1:");
                            Scanner scanner1=new Scanner(System.in);
                            int page=Integer.parseInt(scanner1.next());
                            if (page<=(size+1) && page>=1){
                                --page;
                                x1=page*10;
                            }else {
                                System.out.println("请输入小于"+(size+1)+"的页数或者大于1");
                            }
                        }else {
                            break;
                        }
                    }
                }
            }
        }
        printMenu();
        }
    public void printMenu(){
        System.out.println("1.新增学生");
        System.out.println("2.删除学生");
        System.out.println("3.修改学生");
        System.out.println("4.查询学生");
        System.out.println("5.遍历学生");
        System.out.println("0.退出");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.next();
        if (action.equals("1")) {
            addStudent();
        } else if (action.equals("2")) {
            delStudent();
        } else if (action.equals("3")) {
            changeStudent();
        } else if (action.equals("4")) {
            getStudent();
        }else if(action.equals("5")){
            allStudent();
        }else {
            Day3.printMenu();
        }

    }
}



