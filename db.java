import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    static String url="jdbc:mysql://localhost:3306/inventory";
    static String user="root";
    static String password="Harshenii_2005";
    public static Connection getconnection() throws SQLException{
        return DriverManager.getConnection(url,user,password);
    }
}
