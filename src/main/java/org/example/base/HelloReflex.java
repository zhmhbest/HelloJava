package org.example.base;

public class HelloReflex {
    static class Person {
        //私有属性
        public String name;
        public void sayName(){
            System.out.println("Person: " + this.name);
        }
    }

    public static void main(String[] args) {
        // 包名.类名$内部类名
        final String ClassName = Person.class.toString().split(" ")[1];

        Class cls1 = Person.class;
        System.out.println(cls1);

        Person p1 = new Person();
        Class cls2 = p1.getClass();
        System.out.println(cls2);

        try {
            Class cls3 = Class.forName(ClassName);
            System.out.println(cls3);
            Person person = (Person) cls3.newInstance();
            person.name = "Reflex";
            person.sayName();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
