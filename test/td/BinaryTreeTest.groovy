package td

// To download GMongo on the fly and put it at classpath
// @Grab(group='com.gmongo', module='gmongo', version='0.9.5')

// @Grab('org.codehaus.gpars:gpars:0.10')
import com.mongodb.DB
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import td.mongo.MongoDB

/**
 * Created with IntelliJ IDEA.
 * User: mikher
 * Date: 2012-05-24
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */

class BinaryTreeTest extends GroovyTestCase {

    DB db

    def mongoHQ = {
        log.info("Setting up test for DB HQ")
        MongoDB.getConnectionMongoHQ()}
    def mongoLab = {
        log.info("Setting up test for Lab")
        MongoDB.getConnectionMongoLab()
    }
    def mongoTD = {
        log.info("Setting up test for DB TD")
        MongoDB.getConnectionTD()
    }


    void setUp() {
        log.info("Setting up test for DB")
        // db = MongoDB.getConnectionTD()
        db = mongoTD()

    }

    void testGetConnection() {

        def colls = db.getCollectionNames()

        log.info("Collections in ${db.getName()}:")
        colls.each { c ->
            println c
        }

        assert colls.size() > 0
    }

    void testGetConnectionTD() {


        DB tdDB = MongoDB.getConnectionMongoHQ()

        println tdDB
        assert tdDB != null
        // def colls = db.getCollectionNames()

        // log.info("Collections in ${db.getName()}:")
        // colls.each { c ->
        //    println c
        //}

        //assert colls.size() > 0
    }

    void testGetCollection() {
        assertNotNull db.testCollection
    }

    void insertDoc(def n) {
        println n

        def doc = [:]
        doc.name = "MongoDB"
        doc.id = "id" + n
        doc.count = n

        def infodoc = [x: 203, y: 102]
        doc << infodoc

        db.testCollection2 << doc

        println doc

        // DBObject myDoc = coll.findOne()

        // assert myDoc.get("name") == "MongoDB"
    }

    void testInsertMany() {
        db.manyCollection.drop()
        // db.getCollection("manyCollection").drop()
        def infodata = [:]
        (1..100).each { i ->
            infodata.x$i = 1234567890;
        }
        def NR_OF_DOCS = 10000
        timeCallWithDescription("Inserted ${NR_OF_DOCS} docs of 1000Bytes in ",
                {
                    (1..NR_OF_DOCS).each {n ->
                        db.manyCollection << [name: "mongoDB" + n, type: "database", count: n, info: infodata]
                    }
                }
        )
        assert db.manyCollection.count == NR_OF_DOCS;
    }

    void testInsertDocsInParallell() {
        def nrOfParallellUpdates = 10

        timeCallWithDescription("execute create of $nrOfParallellUpdates documents non parallell",
                {
                    (1..nrOfParallellUpdates).each { i ->
                        insertDoc(i)
                    }
                }
        )

        timeCallWithDescription("executing create of $nrOfParallellUpdates documents in:",
                {
                    (1..nrOfParallellUpdates).each { i ->
                        Thread.start {
                            insertDoc(i)
                        }
                    }
                }
        )

    }

    void testUpdateConflicts() {
        db.names << [name: "Mikael", id: 1]
        db.names << [name: "Evvy", id: 2]
        db.names << [name: "Ella", id: 3]
        db.names << [name: "Theo", id: 4]


        def mikael = db.names.findOne()

    }

    void testMoreThanZeroDocInCollection() {

        db.names << [name: "Mikael", id: 1]
        db.names << [name: "Evvy", id: 2]
        db.names << [name: "Ella", id: 3]
        db.names << [name: "Theo", id: 4]

        DBCollection coll = db.getCollection("names")
        DBCursor cursor = coll.find()
        println cursor.count()

        assert cursor.count() > 0
    }

    void testString() {
        def NR_OF_DOCS = 10000
        String string2 = "otto"
        println "test $NR_OF_DOCS in" + System.currentTimeMillis() + " time"
    }

    void testHowMany() {
        MongoDB db = new MongoDB()
        DB connection = db.getConnectionMongoHQ()
        DBCollection coll = connection.getCollection("manyCollection")
        println coll.count
    }

    // Helper Closure to time method calls
    def timeCallWithDescription = { description, Closure method ->
        def start = System.currentTimeMillis()
        method.call()
        return println(description + " " + ((System.currentTimeMillis() - start) / 1000))
    }

    def printTime = timeCallWithDescription.curry("")

    void testPrintTime() {
        printTime {println "time to print this line"}
    }



    void testGetDb() {
        assert MongoDB.getDB()
    }

    void testDynamicMaps() {
        def info = [:]

        (1..100).each { i ->
            info.x$i = 1234567890;
        }

        assert info
    }

}
