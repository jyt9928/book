package zooInterface.impl;

import zooInterface.Employee;

public class Feeder implements Employee {
    private String name;
    public void work(){
        System.out.println("Feeder the animal");
    }

    public void setName(String name) {
        this.name=name;
    }
}
