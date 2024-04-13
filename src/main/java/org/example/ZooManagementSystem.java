package org.example;

import zooInterface.impl.Feeder;
import zooInterface.impl.ZooKeeper;

import java.util.*;


public class ZooManagementSystem {



    public static void main(String[] args){
        Bird bird1=new Bird();
        Mammal mammal1=new Mammal();
        ZooKeeper zooKeeper1=new ZooKeeper();
        Feeder feeder1=new Feeder();
        Bird bird2=new Bird();
        mammal1.setName("猴子");
        zooKeeper1.setName("张三");
        feeder1.setName("王五");
        bird1.setName("polly");
        bird1.setColor("白色");
        bird2.setName("polly");
        bird2.setColor("黑色");
        if(bird1.equals(bird2)){
            System.out.println("是同一只鸟");
        }else {
            System.out.println("不是同一只鸟");
        }
        bird1.equals(bird1);
        /*
        * arrayList
        */
        ArrayList<Integer>arrayListDemo =new ArrayList<Integer>();
        arrayListDemo.add(1);
        arrayListDemo.add(2);
        arrayListDemo.add(3);
        System.out.println(arrayListDemo);
        arrayListDemo.set(0,2);
        System.out.println(arrayListDemo);
        arrayListDemo.remove(0);
        System.out.println(arrayListDemo);
        System.out.println(arrayListDemo.size());
        for (int x=0;x<arrayListDemo.size();x++){
            System.out.println(arrayListDemo.get(x));
        }

        /*
        * linkedList
        */
        LinkedList<String> linkedListDemo=new LinkedList<>();
        linkedListDemo.add("car");
        linkedListDemo.add("job");
        linkedListDemo.add("interesting");
        linkedListDemo.add("run");
        System.out.println(linkedListDemo);
        linkedListDemo.addFirst("car1");
        System.out.println("头添加"+linkedListDemo);
        linkedListDemo.addLast("run2");
        System.out.println("尾添加"+linkedListDemo);
        linkedListDemo.removeFirst();
        System.out.println("头删除"+linkedListDemo);
        linkedListDemo.removeLast();
        System.out.println("尾删除"+linkedListDemo);
        System.out.println("头获取"+linkedListDemo.getFirst());
        System.out.println("尾获取"+linkedListDemo.getLast());
        linkedListDemo.forEach(System.out::println);

        /*
        *hashSet
        * */
        HashSet<Character>hashSetDemo=new HashSet<>();
        hashSetDemo.add('a');
        hashSetDemo.add('b');
        hashSetDemo.add('c');
        hashSetDemo.add('d');
        System.out.println("是否存在："+hashSetDemo.contains('c'));
        hashSetDemo.remove('c');
        System.out.println("删除元素："+hashSetDemo);
        System.out.println("个数:"+hashSetDemo.size());
        for (int x=0;x<hashSetDemo.size();x++){
            System.out.println("遍历:"+x+hashSetDemo.getClass());
        }
        /*
        * hashMap
        * */
        HashMap<Integer,String>hashMapDemo=new HashMap<>();
        hashMapDemo.put(1,"RT");
        hashMapDemo.put(2,"ET");
        hashMapDemo.put(3,"CT");
        System.out.println(hashMapDemo);
        System.out.println("查看"+hashMapDemo.get(3));
        hashMapDemo.remove(1);
        System.out.println(hashMapDemo);
        for (Integer x:hashMapDemo.keySet()){
            System.out.println("遍历:"+x+hashMapDemo.get(x));
        }

    }
}
