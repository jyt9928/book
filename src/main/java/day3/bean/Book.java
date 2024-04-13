package day3.bean;

import java.util.Date;

public class Book {
    //    添加图书信息
    private String bookName;
    private String bookAuthor;
    private String bookPress;
    private String borrowBookStudentName;
    private int totalBook;
    private int residueBook;
    private int borrowBook;
    private long createOn;

    public String getBorrowBookStudentName() {
        return borrowBookStudentName;
    }

    public void setBorrowBookStudentName(String borrowBookStudentName) {
        this.borrowBookStudentName = borrowBookStudentName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPress() {
        return bookPress;
    }

    public long getCreateOn() {
        return createOn;
    }

    public void setCreateOn(long createOn) {
        this.createOn = createOn;
    }

    public void setBookPress(String bookPress) {
        this.bookPress = bookPress;
    }
    public int getTotalBook() {
        return totalBook;
    }

    public void setTotalBook(int totalBook) {
        this.totalBook = totalBook;
    }

    public int getResidueBook() {
        return residueBook;
    }

    public void setResidueBook(int residueBook) {
        this.residueBook = residueBook;
    }

    public int getBorrowBook() {
        return borrowBook;
    }

    public void setBorrowBook(int borrowBook) {
        this.borrowBook = borrowBook;
    }

}
