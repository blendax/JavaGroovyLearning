package td.interfaces;

/**
 * Created by mikher on 2014-01-16.
 */
public class Fiat implements Car {

    @Override
    public void startUpCar() {
        System.out.println("Starting the Fiat");
    }


    @Override
    public int getPetrolLevel() {
        return 23;
    }

    @Override
    public String getBrand() {
        return "Fiat";
    }
}
