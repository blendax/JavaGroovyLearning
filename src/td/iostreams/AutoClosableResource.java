package td.iostreams;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by mikher on 2014-01-16.
 */
public class AutoClosableResource implements Closeable {

    @Override
    public void close() throws IOException{
        System.out.println("Closing resource AutoClosableResource");
        // throw new IO("This is an error while closing the resorce");
    }

    public AutoClosableResource() throws  IOException{
        // throw new IOException("IO Exception from AutoClosableResource");
        // throw new RuntimeException("Error when creating the resource");
    }

    public void sayHello() {
        System.out.println("Hello");
    }
}
