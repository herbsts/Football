package herbsts.testapp;

/**
 * Created by Stefan Herbst on 13.03.2017.
 */
//new comment
public class Car {
    private String name = null;
    private int ps = -1;
    private int constructed = -1;

    public Car(String name, int ps, int constructed) {
        this.name = name;
        this.ps = ps;
        this.constructed = constructed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getConstructed() {
        return constructed;
    }

    public void setConstructed(int constructed) {
        this.constructed = constructed;
    }

    @Override
    public String toString() {
        return name+ps+constructed;
    }
}