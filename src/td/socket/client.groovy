package td.socket

/**
 * Created with IntelliJ IDEA.
 * User: mikher
 * Date: 2014-01-15
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
class Client {



    def sendData = { input, output ->

        output << "echo line 1\n"
        output << "echo line 2\n"
        output << "echo line 3\n"
        output << "echo line 4\n"
        output << "exit client\n"

        //output << "*bye*\n"
        def reader = input.newReader()

        def buffer = null

        while((buffer = reader.readLine()) != null && !buffer.startsWith("XXXserver-response: exit client")) {
            println "response = $buffer"
        }

        println "Client exiting"
    }

    void getSocket(String host, int port) {

        def s = new Socket(host, port);

        s.withStreams{ input, output -> sendData(input, output) }


    }

}