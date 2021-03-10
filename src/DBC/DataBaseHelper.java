package DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {
    public static Connection getConnection(){
        try {
         //   Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/frabbi", "root", "1234");
            return con;
        } catch (SQLException e) {
            System.err.println("Connection error");
            return null;
        }
    }
}
