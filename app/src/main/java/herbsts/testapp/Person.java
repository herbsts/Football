package herbsts.testapp;

/**
 * Created by Stefan Herbst on 13.03.2017.
 */

public class Person {
    private String name = null;
    private int age = -1;
    private Car car = null;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name+age+car;
    }
}