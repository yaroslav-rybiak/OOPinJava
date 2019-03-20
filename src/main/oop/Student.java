package oop;

public class Student implements Person {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    Student(String name) {
        this.name = name;
        this.id = "unknown id";
    }

    Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String toString() {
       return id + ": " + getName();
    }

    public static void main(String[] args) {
        Person p = new Student("Eric", "007");
        Person q = new Student("Anna");
        Person r = new Student("Rose");
        Person s = new Student("John", "008");
        System.out.println(p.toString());
        System.out.println(q.toString());
        System.out.println(r.toString());
        System.out.println(s.toString());
    }
}
