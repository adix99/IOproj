package database;

import java.sql.Statement;

public interface DataBaseConnect {
    public static Statement st = Database.connectToDatabase("BankManager", "root", "Adix_23/09/1999");
}
