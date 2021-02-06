class Person {
    //私有属性
    public String name;
    public void sayName(){
        System.out.println(this.name);
    }
}

public class demoReflex {
    public static void main(String[] args) {
        Class cls1 = Person.class;

        Person p1 = new Person();
        Class cls2 = p1.getClass();

        try {
            Class cls = Class.forName("Person"); // 包名.类名
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
