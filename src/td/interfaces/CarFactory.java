package td.interfaces;

/**
 * Created by mikher on 2014-01-16.
 */
public class CarFactory {

    public static Car getCar() {

        Car[] cars = new Car[2];

        cars[0] = new Volvo();
        cars[1] = new Fiat();
        return cars[getZeroOrOne()];
    }

    public static int getZeroOrOne() {
        int result = (int) Math.round(Math.random());
        return result;
    }

}
