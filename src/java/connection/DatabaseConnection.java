
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnection
{
   private static DatabaseConnection instance;
    private Connection connection;
    
    private DatabaseConnection()
    {
        ResourceBundle prop = ResourceBundle.getBundle("connection.database");
        try 
        {
            Class.forName(prop.getString("DRIVER"));
            connection = DriverManager.getConnection(prop.getString("PROTOCOL")+"://"+
                    prop.getString("HOST")+"/"+prop.getString("DATABASE")
                    +";useUnicode=yes&characterEncoding=UTF-8",prop.getString("USERNAME"),
                    prop.getString("PASSWORD"));
            
            
        } catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DatabaseConnection getInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection()
    {
        return connection;
    } 
}
