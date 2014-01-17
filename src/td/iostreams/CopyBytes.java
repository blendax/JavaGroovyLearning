package td.iostreams;

/**
 * Created with IntelliJ IDEA.
 * User: mikher
 * Date: 2014-01-16
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {

    public static void main(String[] args) {

        // Try with resource
        try (FileInputStream in = new FileInputStream("xanadu.txt");
             FileOutputStream out = new FileOutputStream("outagain.txt");
             AutoClosableResource acr = new AutoClosableResource();
        ) {
            acr.sayHello();
            int c;

            while ((c = in.read()) != -1) {
                // Chnage in day 3
                out.write(c);
            }
            // in.close
        } catch (IOException ioe) {
            /*Throwable[] suppressed = ioe.getSuppressed();
            for (int i = 0; i < suppressed.length; i++) {
                suppressed[i].printStackTrace();
            }
            */
            ioe.printStackTrace();
        }

        /*finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } */
    }
}

