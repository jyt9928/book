package org.example;

import java.util.Objects;

public class Bird extends Animal{
    public String name;
    private String color;
    public void fly(){
        System.out.println("飞");
    }
    public void setName(String name){
        this.name=name;
    }
    public void setColor(String color){
        this.color=color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return Objects.equals(name, bird.name);

    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
