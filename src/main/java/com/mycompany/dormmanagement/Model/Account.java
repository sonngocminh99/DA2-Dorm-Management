/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import connect.DataConnection;
import java.lang.Runtime.Version;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.StatementEvent;
/**
 *
 * @author Mayy
 */
public class Account {
    protected String IDAccount;
    protected String Username;
    protected String Password;
    protected String Permission;

    public Account(String IDAccount, String Username, String Password, String Permission) {
        this.IDAccount = IDAccount;
        this.Username = Username;
        this.Password = Password;
        this.Permission = Permission;
    }
    
    public Account() {
        this.IDAccount = "";
        this.Password = "";
        this.Permission = "";
        this.Username = "";
    }

    public String getIDAccount() {
        return IDAccount;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getPermission() {
        return Permission;
    }

    public void setIDAccount(String IDAccount) {
        this.IDAccount = IDAccount;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setPermission(String Permission) {
        this.Permission = Permission;
    }
    public void GetDataByUsername(String username) {
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from account where Username = '" + username + "' and active ='yes'";
            resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                setIDAccount(resultSet.getString(1));
                setUsername(resultSet.getString(2));
                setPassword(resultSet.getString(3));
                setPermission(resultSet.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
    
    }   
    public void getDataByID(String ID){
    Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from account where IDAccount = '" + ID + "' and active='yes'";
            resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                setIDAccount(resultSet.getString(1));
                setUsername(resultSet.getString(2));
                setPassword(resultSet.getString(3));
                setPermission(resultSet.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if ( resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
    }
     public void insertNewAccount(){
     Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="insert into account(IDAccount,Username,Password,Permission,active)values(?,?,?,?,'yes')" ;
            statement = con.prepareStatement(query);
            statement.setString(1, this.IDAccount);
            statement.setString(2, this.Username);
            statement.setString(3, this.Password);
            statement.setString(4, this.Permission);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
     
     }
     public void update(Account account){
     Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="update account set Password=?,Permission=? where IDAccount=?" ;
            statement = con.prepareStatement(query);
            statement.setString(1, account.getPassword());
            statement.setString(2, account.getPermission());
            statement.setString(3, account.getIDAccount());
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
     
     }
     public void detele(String ID){
     
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="update account set active='no' where IDAccount=?" ;
            statement = con.prepareStatement(query);
            statement.setString(1, ID);
            statement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
     }
}
