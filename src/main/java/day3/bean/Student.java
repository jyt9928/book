package day3.bean;

public class Student {
    private String StName;
    private String borrowBookISBN;

    public String getStName() {
        return StName;
    }

    public void setStName(String stName) {
        StName = stName;
    }
    public String getBorrowBookISBN(){
        return this.borrowBookISBN;
    }
    public void setBorrowBookISBN(String borrowBookISBN){
        this.borrowBookISBN=borrowBookISBN;
    }
}
