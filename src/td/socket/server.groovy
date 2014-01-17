package td.socket

import java.net.ServerSocket
/**
 * Created with IntelliJ IDEA.
 * User: mikher
 * Date: 2014-01-15
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
class Server {

    void startServer() {

        def server = new ServerSocket(4242)

        while (true) {
            server.accept { socket ->
                println "processing new connection..."
                socket.withStreams { input, output ->
                    def reader = input.newReader()
                    def buffer = null
                    while ((buffer = reader.readLine()) != null) {
                        //def buffer = reader.readLine()
                        println "server received: $buffer"
                        if (buffer == "*bye*") {
                            println "exiting..."
                            System.exit(0)
                        } else {
                            output << "server-response: " + buffer + "\n"
                        }
                    }
                }
                println "processing complete."
            }
        }
    }
}

// Client side

/*
s = new Socket("localhost", 4242);
s.withStreams { input, output ->
    output << "echo line 1\n"
    output << "echo line 2\n"
    output << "echo line 3\n"
    output << "echo line 4\n"

//output << "*bye*\n"
    reader = input.newReader()
    buffer = reader.readLine()
    println "response = $buffer"
}
*/