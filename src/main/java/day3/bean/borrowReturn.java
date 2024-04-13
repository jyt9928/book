package day3.bean;

import java.util.Date;

public class borrowReturn {
    private long bookISBN;
    private int studentId;
    private Date borrowTime;
    private Date returnTime;

    public long getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(long bookISBN) {
        this.bookISBN = bookISBN;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }
}
