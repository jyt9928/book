package org.example;

public class Person {
    String yan = "黑色"; //眼睛颜色
    String pifu = "黑色"; //皮肤颜色
    String toufa = "黑色"; //头发颜色
    private String yuyan;//语言

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
        this.name = "未命名";
    }
    public void applyName(String newName) {
        if (this.name.equals("未命名")) {
            this.name = newName;
        }
    }
    public String suohua() {
        return "大家好我叫" + name + "我说" + yuyan;
    }

}
