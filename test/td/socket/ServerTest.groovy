package td.socket

/**
 * Created with IntelliJ IDEA.
 * User: mikher
 * Date: 2014-01-15
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
class ServerTest extends GroovyTestCase {

    void testStartServer() {
        Server server = new Server();
        server.startServer();
    }

    void tearDown() {

    }

    void testClient() {
        Client client = new Client();
        client.getSocket("localhost", 4242);
    }
}
