package org.example;

public class Mammal extends Animal{
    private String name;
    public void eat(){

    }
    public void setName(String name){
        this.name=name;
    }
    public void addName(String name){
        if (this.name.equals(null)){
            this.name=name;
        }

    }


}
