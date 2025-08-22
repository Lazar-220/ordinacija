/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.server.repository.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author milos
 */
public class DbConnectionFactory {
    
    private Connection connection;
    private static DbConnectionFactory instance;
    
    public static DbConnectionFactory getInstance() {
        if(instance==null){
            instance=new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    

    private DbConnectionFactory() {
        
        try {
            if(connection==null||connection.isClosed()){
                String url=rs.ac.bg.fon.ai.server.konfiguracija.Konfiguracija.getInstance().getProperty("url");
                String username=rs.ac.bg.fon.ai.server.konfiguracija.Konfiguracija.getInstance().getProperty("username");
                String password=rs.ac.bg.fon.ai.server.konfiguracija.Konfiguracija.getInstance().getProperty("password");
                connection=DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
