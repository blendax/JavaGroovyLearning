package td.interfaces;

/**
 * Created by mikher on 2014-01-16.
 */
public class Volvo implements Car {

    @Override
    public void startUpCar() {
        System.out.println("Starting the Volvo");
    }

    @Override
    public int getPetrolLevel() {
        return 23;
    }

    @Override
    public String getBrand() {
        return "Volvo";
    }
}
