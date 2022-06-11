/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */

//phần này dùng để xài trong chương trình sẽ có video hướng dẫn cách code với cách xài connect này nha
public class DataConnection extends Config {
    
    public static Connection dbconnection;
    
    public static Connection getConnection() {
        String connectionString = "jdbc:mysql://"+ Config.dbhost+ ":"+ Config.dbport+ "/"+ Config.dbname;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dbconnection = DriverManager.getConnection(connectionString, Config.dbuser, Config.dbpass);
            if (dbconnection != null) {
                
            } else {
                System.out.print("fail");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dbconnection;
    }
    
//    public static void main(String[] args) {
//        Connection dbconnection;
//        String connectionString = "jdbc:mysql://"+ Config.dbhost+ ":"+ Config.dbport+ "/"+ Config.dbname;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            dbconnection = DriverManager.getConnection(connectionString, Config.dbuser, Config.dbpass);
//            if (dbconnection != null) {
//                System.out.print("Success .......");
//            } else {
//                System.out.print("fail");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
