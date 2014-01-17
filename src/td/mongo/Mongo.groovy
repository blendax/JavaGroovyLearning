package td.mongo

import com.mongodb.Mongo;
import com.mongodb.DB;


import com.mongodb.DBAddress
import com.gmongo.GMongo;



/**
 * Created with IntelliJ IDEA.
 * User: mikher
 * Date: 2012-05-24
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
final class MongoDB {

    //mongodb://tdtest:tdtest@staff.mongohq.com:10097/TDMongoFree16MB


    private static final String DB_NAME_MONGO_HQ = "TDMongoFree16MB"
    private static final String DB_NAME_MONGO_LAB = "mongotest240mbfree"
    private static final String DB_NAME_TD = "mongotestTD"


    public static final DB getConnectionMongoHQ() {
        // mongodb://[username@password:]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/database]
        // Mongo m = new Mongo("mongodb://tdtest:tdtest@staff.mongohq.com:10097/TDMongoFree16MB");
        // mongodb://fred:foobar@localhost/
        // Mongo m = new Mongo('mongodb://tdtest:tdtest@staff.mongohq.com:10097');

        GMongo m = new GMongo(new DBAddress("staff.mongohq.com", 10097, DB_NAME_MONGO_HQ))
        m.getDB(DB_NAME_MONGO_HQ).authenticate("tdtest", 'tdtest'.getChars())
        m.getDB DB_NAME_MONGO_HQ
    }

    // ds033297.mongolab.com:33297/mongotest240mbfree

    public static final DB getConnectionMongoLab() {
        // mongodb://[username@password:]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/database]
        // Mongo m = new Mongo("mongodb://tdtest:tdtest@staff.mongohq.com:10097/TDMongoFree16MB");
        // mongodb://fred:foobar@localhost/
        // Mongo m = new Mongo('mongodb://tdtest:tdtest@staff.mongohq.com:10097');

        /*Mongo m = new Mongo(new DBAddress("ds033297.mongolab.com", 33297, DB_NAME_MONGO_LAB))
        m.getDB(DB_NAME_MONGO_LAB).authenticate("tdtest", 'tdtest'.getChars())
        m.getDB DB_NAME_MONGO_LAB*/

        GMongo m = new GMongo(new DBAddress("ds033297.mongolab.com", 33297, DB_NAME_MONGO_LAB))
        m.getDB(DB_NAME_MONGO_LAB).authenticate("tdtest", 'tdtest'.getChars())
        m.getDB DB_NAME_MONGO_LAB
    }

    public static final DB getConnectionTD() {
        // mongodb://[username@password:]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/database]
        // Mongo m = new Mongo("mongodb://tdtest:tdtest@staff.mongohq.com:10097/TDMongoFree16MB");
        // mongodb://fred:foobar@localhost/
        // Mongo m = new Mongo('mongodb://tdtest:tdtest@staff.mongohq.com:10097');

        // Mongo m = new Mongo(new DBAddress("127.0.0.1", 27017, DB_NAME_TD))
        // m.getDB(DB_NAME_TD).authenticate("tdtest", 'tdtest'.getChars())
        // m.getDB DB_NAME_TD


        def GMongo mongo = new GMongo(new DBAddress("127.0.0.1", 27017, DB_NAME_TD) )
        // def names = mongo.getDatabaseNames()
        return mongo.getDB(DB_NAME_TD)


    }

    public static final DB getDB() {

        def GMongo mongo = new GMongo("ds033297.mongolab.com", 33297)
        def db = mongo.getDB(DB_NAME_MONGO_LAB)
        db.authenticate("tdtest", 'tdtest'.getChars())


        return db
    }




// or
    //Mongo m = new Mongo( "localhost" );
// or
    //Mongo m = new Mongo( "localhost" , 27017 );

    //DB db = m.getDB( "mydb" );

}
