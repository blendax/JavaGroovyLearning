package td.interfaces;

/**
 * Created by mikher on 2014-01-16.
 */
public class CarUser {

    private String brand;

    public void useTheCar() {
        Car car = CarFactory.getCar();
        car.startUpCar();
        System.out.println("Petrol level is=" + car.getPetrolLevel());
        brand = car.getBrand();
        System.out.println("Brand=" + brand);
    }

    public String getBrand() {
        return brand;
    }
}
