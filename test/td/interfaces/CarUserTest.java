package td.interfaces;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by mikher on 2014-01-16.
 */
public class CarUserTest {

    @Test
    public void testUseTheCar() throws Exception {
        for (int i = 0; i < 10; i++) {
            CarUser cu = new CarUser();
            cu.useTheCar();

            String brand = cu.getBrand();
            assertTrue("Car is Volvo or Fiat", brand.equals("Volvo") || brand.equals("Fiat"));
        }
    }
}
