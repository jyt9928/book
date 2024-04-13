package day3;

import day3.bean.Book;
import day3.bean.Student;
import jdbc.MysqlJdbc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BookAction implements Action {
    /*
     * 添加书
     * */
    public void addBook(String bookName, String bookAuthor, String bookPress, long bookISBN, int totalBook, int residueBook, int borrowBook) {
        ArrayList<Map<String, Object>> bookTable = MysqlJdbc.selectSql("select * from book");
        Map<String, Object> map = new HashMap<>();
        map.put("bookName", bookName);
        map.put("bookAuthor", bookAuthor);
        map.put("bookPress", bookPress);
        map.put("bookISBN", bookISBN);
        map.put("totalBook", totalBook);
        map.put("residueBook", residueBook);
        map.put("borrowBook", borrowBook);
        map.put("createon",System.currentTimeMillis());
        if (MysqlJdbc.inspectSql("book", "bookISBN", bookISBN)) {
            System.out.println("输入失败重复ISBN码");
        } else {
            bookTable.add(map);
            MysqlJdbc.joinSql("book", map.keySet().toArray(new String[0]), map.values().toArray(new Object[0]));
            System.out.println("输入成功");
        }
    }

    /*
     * 删除书籍
     * */
    public void delBook() {
        System.out.println("输入删除书籍的ISBN吗 输入0退出");
        Scanner del = new Scanner(System.in);
        String del1 = del.next();
        if (!del1.equals("0")) {
            if (MysqlJdbc.delSql("book", "bookISBN", Long.parseLong(del1))) {
                System.out.println("删除成功");
                printMenu();
            } else {
                System.out.println("删除失败无此字段");
                printMenu();
            }
        } else {
            printMenu();
        }
    }

    /* * 查询
     * */
    public void getBook() {
        System.out.println("输入书籍ISBN或者书籍名查询,输入0退出");
        Scanner get = new Scanner(System.in);
        String bookName = get.next();
        int x = 10;
        int x1 = 0;
        if (!bookName.equals("0")) {
            while (true) {
                ArrayList<Map<String, Object>> select = MysqlJdbc.selectSql("SELECT * FROM book WHERE bookname LIKE ? OR bookISBN like ? LIMIT " + x + " OFFSET " + x1, bookName, bookName);
                for (Map map : select) {
                    System.out.println(map);
                }
                int size = select.size()/10;
                if (size==0){
                    size++;
                }

                if (select == null) {
                    System.out.println("查询失败无此书名或类似的书");
                    printMenu();
                } else {
                    if ((x1 / 10) == (size-1) && x1 == 0) {
                        System.out.println("1.选择跳转到第几页 0.退出");
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
    /* * 更改书籍
     * */
    public void changeBook() {
        System.out.println("请输入要更改书籍的ISBN,输入0退出");
        Scanner set = new Scanner(System.in);
        long set1 = Long.parseLong(set.next());
        if (MysqlJdbc.inspectSql("book","bookISBN",set1)){
            if (set1 != 0) {
                System.out.println("选择要修改的参数");
                System.out.println("1.出版社");
                System.out.println("2.作者");
                System.out.println("3.总书量");
                System.out.println("4.剩余书量");
                System.out.println("5.借书量");
                System.out.println("输入0退出");
                System.out.println("如果要修改ISBN码请删除后重新添加");
                Scanner sel = new Scanner(System.in);
                String sel1 = sel.next();
                if (sel1.equals("1")){
                    System.out.println("输入要更改的值");
                    Scanner scanner=new Scanner(System.in);
                    String value=scanner.next();
                    if (MysqlJdbc.changeSql("book","bookpressn",set1,value,"bookISBN")==0){
                        System.out.println("输入失败");
                        printMenu();
                    }else {
                        System.out.println("输入成功");
                        printMenu();
                    }

                }else if(sel1.equals("2")){
                    System.out.println("输入要更改的值");
                    Scanner scanner=new Scanner(System.in);
                    String value=scanner.next();
                   if (MysqlJdbc.changeSql("book","bookauthor",set1,value,"bookISBN")==0){
                       System.out.println("输入失败");
                       printMenu();
                   }else {
                       System.out.println("输入成功");
                       printMenu();
                   }
                }else if (sel1.equals("3")){
                    System.out.println("输入要更改的值");
                    Scanner scanner=new Scanner(System.in);
                    String value=scanner.next();
                   if (MysqlJdbc.changeSql("book","totalbook",set1,value,"bookISBN")==0){
                       System.out.println("输入失败");
                       printMenu();
                   }else {
                       System.out.println("输入成功");
                       printMenu();
                   }
                }else if (sel1.equals("4")){
                    System.out.println("输入要更改的值");
                    Scanner scanner=new Scanner(System.in);
                    String value=scanner.next();
                    if (MysqlJdbc.changeSql("book","residuebook",set1,value,"bookISBN")==0){
                        System.out.println("输入失败");
                        printMenu();
                    }else {
                        System.out.println("输入成功");
                        printMenu();
                    }
                }else if (sel1.equals("5")){
                    System.out.println("输入要更改的值");
                    Scanner scanner=new Scanner(System.in);
                    String value=scanner.next();
                    if (MysqlJdbc.changeSql("book","borrowbook",set1,value,"bookISBN")==0){
                        System.out.println("输入失败");
                        printMenu();
                    }else {
                        System.out.println("输入成功");
                        printMenu();
                    }
                }else{
                    printMenu();
                }
            }
        }else {
            System.out.println("无此书的ISBN");
            printMenu();
        }

    }
    /*
    * 遍历
    * */
    public void allBook() {
        int x1 = 0;
        int x2 = 10;
        int size = MysqlJdbc.selectSql("SELECT * FROM book").size() / 10;
        if (size == 0) {
            size++;
        }
        for (; true; ) {
            ArrayList<Map<String, Object>> all = MysqlJdbc.allSql("SELECT * FROM book LIMIT ? OFFSET ?", x2, x1);
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

                    public void printMenu(){
            System.out.println("1:添加书籍");
            System.out.println("2:删除书籍");
            System.out.println("3:查询书籍");
            System.out.println("4:更改书籍");
            System.out.println("5.查看全部书籍");
            System.out.println("0:退出");
            Scanner scanner = new Scanner(System.in);
            String decide = scanner.next();
            if (decide.equals("1")) {
                System.out.println("输入要添加的书籍用空逗号隔开书籍间的数据,输入顺序为书名,作者,出版社,书的ISBN,总书量,剩余书量,借出的书量.输入0退出循环:");
                Scanner add = new Scanner(System.in);
                String input = add.next();
                if (!input.equals("0")) {
                    String[] a = input.split(",");
                    addBook(a[0], a[1], a[2], Long.parseLong(a[3]), Integer.parseInt(a[4]), Integer.parseInt(a[5]), Integer.parseInt(a[6]));
                    printMenu();
                } else {
                    printMenu();
                }
            } else if (decide.equals("2")) {
                delBook();
            } else if (decide.equals("3")) {
                getBook();
            } else if (decide.equals("4")) {
                changeBook();
            }else if (decide.equals("5")){
                allBook();
            }else {Day3.printMenu();};

        }
    }
