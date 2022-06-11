/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import connect.DataConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mayy
 */
public class Apartment {
    protected String IDApartment;
    protected String NoRoom;
    protected String Gender;
    protected String IDEmployee;

    public Apartment(String IDApartment, String NoRoom, String Gender, String IDEmployee) {
        this.IDApartment = IDApartment;
        this.NoRoom = NoRoom;
        this.Gender = Gender;
        this.IDEmployee = IDEmployee;
    }
    
    public Apartment() {
        this.IDApartment = "";
        this.NoRoom = "";
        this.Gender = "";
        this.IDEmployee = "";
    }

    public String getIDApartment() {
        return IDApartment;
    }

    public void setIDApartment(String IDApartment) {
        this.IDApartment = IDApartment;
    }

    public String getNoRoom() {
        return NoRoom;
    }

    public void setNoRoom(String NoRoom) {
        this.NoRoom = NoRoom;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getIDEmployee() {
        return IDEmployee;
    }

    public void setIDEmployee(String IDEmployee) {
        this.IDEmployee = IDEmployee;
    }
    
    public ArrayList<String> getApartmentWithGender(String gender){
    
        ArrayList<String> listApartment = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDApartment from apartment where gender = '"+gender+"' and active='yes'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listApartment.add(resultSet.getString(1));
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
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
        return listApartment;
    }
    
    public ArrayList<String> getAllApartment(int option){
    
        ArrayList<String> listApartment = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1:
                    query = "Select IDApartment from apartment where active ='yes'";
                    break;
                case 2:
                    query = "Select IDApartment from apartment where Gender = 'Nam' and active ='yes'";
                    break;
                case 3:
                    query = "Select IDApartment from apartment where Gender = 'Nữ' and active ='yes'";
                    break;
                default:
                    query = "Select IDApartment from apartment where active ='yes'";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listApartment.add(resultSet.getString(1));
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
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
        return listApartment;
    }
    
    public ObservableList<Map<String, Object>> getSearchApartment(int option, String keyWord){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1:
                    query = "Select * from apartment where IDApartment like '%"+ keyWord+"%' and active='yes'";
                    break;
                case 2:
                    query = "Select * from apartment where IDApartment like '%"+ keyWord+"%' and Gender = 'Nam' and active='yes'";
                    break;
                case 3:
                    query = "Select * from apartment where IDApartment like '%"+ keyWord+"%' and Gender = 'Nữ' and active='yes'";
                    break;
                default:
                    query = "Select * from apartment where IDApartment like '%"+ keyWord+"%' and active='yes'";
                    break;
            }
            
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            Employee employee = new Employee();
            employee.getInfoBaseID(resultSet.getString(4));
            item.put("id", resultSet.getString(1));
            item.put("room", resultSet.getString(2));
            item.put("gender", resultSet.getString(3));
            item.put("employee", employee.getFullname());
            items.add(item);
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
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
        return items;
    }
    public ArrayList<String> getRoomNameBaseApartment(String apartment){
    ArrayList<String> listRoom = new ArrayList<>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDRoom from apartment,room where apartment.IDApartment = room.IDApartment and room.IDApartment = '"+apartment+"' and room.active='yes'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listRoom.add(resultSet.getString(1));
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
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
        return listRoom;
    }
    public void insertNewApartment(String ID,String gender,String employeeID){
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="insert into apartment (IDApartment,NoRoom,Gender,IDEmployee,active) values(?,?,?,?,'yes')";
            statement = con.prepareStatement(query);
            statement.setString(1, ID);
            statement.setString(2, "0");
            statement.setString(3, gender);
            statement.setString(4, employeeID);
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
    public void getInfo(String apartment){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from apartment where IDApartment ='"+apartment+"' and active='yes'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.IDApartment = resultSet.getString(1);
              this.NoRoom = resultSet.getString(2);
              this.Gender = resultSet.getString(3);
              this.IDEmployee = resultSet.getString(4);
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
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }  
        
        
    }
   public ArrayList<String> getStudentNameBaseRentBill(String apartment){
    ArrayList<String> listStudent = new ArrayList<>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDStudent from quanlyktx.apartment,quanlyktx.room,quanlyktx.student where apartment.IDApartment = room.IDApartment and room.IDRoom = student.IDRoom and apartment.IDApartment = '"+apartment+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              listStudent.add(resultSet.getString(1));
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
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
        return listStudent;
    }
    public int getTotalRentBill(String apartment,String idroom){
        int Total = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select room.RentingPrice from quanlyktx.apartment,quanlyktx.room where apartment.IDApartment = room.IDApartment and room.IDApartment = '"+apartment+"' and room.IDRoom = '"+idroom+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              Total= resultSet.getInt(1);
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
                Logger lgr = Logger.getLogger(Runtime.Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }        
        return Total;
    }

    public void updateNoRoom(String ID, String noRoom){
    Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="update apartment set NoRoom=? where IDApartment =?";
            statement = con.prepareStatement(query);
            statement.setString(1, noRoom);
            statement.setString(2, IDApartment);
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
    public void delete(String IDApartment){
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="update apartment set active='no' where IDApartment =?";
            statement = con.prepareStatement(query);
            statement.setString(1, IDApartment);
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
