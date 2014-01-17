package td.interfaces;

import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertTrue;

/**
 * Created by mikher on 2014-01-16.
 */
public class CarFactoryTest {

    private static final int NUMBER_OF_RANDOMS = 10000;

    @Test
    public void testGetZeroOrOne() throws Exception {
        int[] stats = {0,0};
        for(int i=0; i< NUMBER_OF_RANDOMS; i++) {
            int randomInt = CarFactory.getZeroOrOne();
            stats[randomInt] = stats[randomInt] + 1;
        }

        assertTrue("Zeros are generated=" + stats[0], stats[0] > NUMBER_OF_RANDOMS /2*0.99);
        assertTrue("Ones are generated=" + stats[1], stats[1] > NUMBER_OF_RANDOMS /2*0.99);
    }
}
