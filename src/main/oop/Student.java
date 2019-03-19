package oop;

/**
 * A class to store information about a Student
 * Used in module 4 of the UC San Diego MOOC Object Oriented Programming in Java
 *
 * @author UC San Diego Intermediate Software Development MOOC team
 */
public class Student extends Person {
    private String id;

    public Student(String name) {
        super(name);
        this.id = "unknown id";
    }

    public Student(String name, String id) {
        super(name);
        this.id = id;
    }

    public boolean isAsleep(int hr) // override
    {
        return 2 < hr && 8 > hr;
    }

    public String toString() {
        return this.id + ": " + super.toString();
    }

    public String report() {
        return "reporting";
    }

    public static void main(String[] args) {
        Student p = new Student("Eric", "007");
        Person q = new Person("Anna");
        Person r = new Student("Rose");
        Person s = new Student("John", "008");
        System.out.println(p.toString());
        System.out.println(q.toString());
        System.out.println(r.toString());
        System.out.println(s.toString());
    }
}
