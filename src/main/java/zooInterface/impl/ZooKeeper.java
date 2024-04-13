package zooInterface.impl;

import zooInterface.Employee;

public class ZooKeeper implements Employee {

    public void work(){
        System.out.println("Cleaning the cage");
    }

    private String name;

    public void setName(String name) {
        this.name=name;
    }
}
