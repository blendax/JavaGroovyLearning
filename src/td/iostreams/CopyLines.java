package td.iostreams;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 *  Read full lines instead of just bytes. Also read characters instead of bytes.
 */
public class CopyLines {

    public static void main(String[] args) throws IOException {
 
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
 
        try {
            inputStream = new BufferedReader(new FileReader("xanadu.txt"));
            // Only outputstreams can be flushed
            outputStream = new PrintWriter(new FileWriter("characteroutput.txt"), true /*Flush buffer for every println*/);
 
            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}