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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mayy
 */
public class RentBill extends Bill{
    private Student student;
    

    public RentBill( String billID,Employee employee , Room room,Apartment apartment,Student student, Date createDay, String total,String status) {
        super(billID, employee, apartment, room, createDay, total,status);
        this.student = student;
    }
    public RentBill(Student student) {
        
        this.student = student;
    }
    public RentBill() {
        super();
        this.student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
public ObservableList<Map<String, Object>> getRentBill(String apartment, int option, String month,String year){
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
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+ "' and YEAR(Createday) = '"+ year+"'";
                    break;
                case 2:
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Đã thu'";
                    break;
                case 3:
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Chưa thu'";
                    break;
                default:
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"'";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("room", resultSet.getString(3));
            item.put("student", resultSet.getString(5));
            item.put("createDay", resultSet.getString(6));
            item.put("total", resultSet.getString(7));
            item.put("status", resultSet.getString(8));
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
    
public ObservableList<Map<String, Object>> getSearchRentBill(String apartment, int option, String keyWord, String month,String year){
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
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+ "' and YEAR(Createday) = '"+ year+"' and (IDRoom LIKE '%" + keyWord +"%' or IDRBill LIKE '%"+keyWord+"%')";
                    break;
                case 2:
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Đã thu' and (IDRoom LIKE '%" + keyWord +"%' or IDRBill LIKE '%"+keyWord+"%')";
                    break;
                case 3:
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and status = 'Chưa thu' and (IDRoom LIKE '%" + keyWord +"%' or IDRBill LIKE '%"+keyWord+"%')";
                    break;
                default:
                    query = "Select IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,format(total,2),status from rentbill where IDApartment ='"+ apartment+"' and MONTH(Createday) = '"+month+"' and YEAR(Createday) = '"+ year+"' and (IDRoom LIKE '%" + keyWord +"%' or IDRBill LIKE '%"+keyWord+"%')";
                    break;
            }
            
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("room", resultSet.getString(3));
            item.put("student", resultSet.getString(5));
            item.put("createDay", resultSet.getString(6));
            item.put("total", resultSet.getDouble(7));
            item.put("status", resultSet.getString(8));
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
    
    public void getInfoBaseIDRentBill(String ID){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {        
            statement = con.createStatement();
            String query = "SELECT rentbill.IDRBill,rentbill.IDApartment,rentbill.IDRoom,rentbill.IDEmployee,employee.Fullname,rentbill.IDStudent,student.Fullname,rentbill.Createday,rentbill.Total,rentbill.status\n" +
                           "FROM ((quanlyktx.rentbill\n" +
                           "INNER JOIN quanlyktx.employee ON rentbill.IDEmployee = employee.IDEmployee)\n" +
                           "INNER JOIN quanlyktx.student ON rentbill.IDStudent = student.IDStudent) \n" +
                           "where rentbill.IDRBill ='"+ID+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.billID = resultSet.getString(1);   
              System.out.println(this.billID);
              this.apartment.getInfo(resultSet.getString(2));
              this.room.getInfo(resultSet.getString(3));
              this.employee.setEmployeeID(resultSet.getString(4));
              this.employee.setFullname(resultSet.getString(5));
              this.student.setStudentID(resultSet.getString(6));
              this.student.setFullName(resultSet.getString(7));

                try {
                    this.createDay = formatter.parse(resultSet.getString(8));
                } catch (ParseException ex) {
                    Logger.getLogger(ElectricAndWaterBill.class.getName()).log(Level.SEVERE, null, ex);
                }
              this.total = nf.format(resultSet.getDouble(9));
              this.status = resultSet.getString(10);
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
     public void updateStatusRentBill(String IDRBill){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query = "update rentbill set status = 'Đã thu' where IDRBill = '"+IDRBill +"'" ;
            statement = con.prepareStatement(query);
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
     public void insertRentBilldata(){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="insert into rentbill(IDRBill,IDEmployee,IDRoom,IDApartment,IDStudent,Createday,Total,status)values(?,?,?,?,?,?,?,?)" ;
            statement = con.prepareStatement(query);
            statement.setString(1, this.billID);
            statement.setString(2, this.employee.getEmployeeID());
            statement.setString(3, this.room.getRoomID());
            statement.setString(4, this.apartment.getIDApartment());
            statement.setString(5, this.student.getStudentID());
            java.sql.Date sqlDate = new java.sql.Date(this.createDay.getTime());
            statement.setDate(6, sqlDate);
            statement.setDouble(7, Double.parseDouble(total));
            statement.setString(8, this.status);
           
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
    public int getLastBillIDIndex(){
        String lastRentBill = "";
        int index = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select IDRBill from rentbill" ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                lastRentBill=resultSet.getString(1);             
                int tempindex = Integer.parseInt(lastRentBill.substring(2));
                if(index<=tempindex){
                index = tempindex;
                }
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
        System.out.println(index);
        return index;
    }
    
    public ArrayList<Double> getTotalRentBill(String month,String year,int option){
        ArrayList<Double> items = new ArrayList<Double>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1: 
                    query ="Select Total from rentbill where MONTH(Createday) = '"+month+ "' and YEAR(Createday) = "+ year ;
                    break;
                case 2:
                    query = "Select Total from rentbill where MONTH(Createday) = '"+month+ "' and YEAR(Createday) = '"+ year+"' and status = 'Đã thu'";
                    break;
                case 3:
                    query = "Select Total from rentbill where MONTH(Createday) = '"+month+ "' and YEAR(Createday) = '"+ year+"' and status = 'Chưa thu'";
                    break;
                default:
                    query = "Select Total from rentbill where MONTH(Createday) = '"+month+ "' and YEAR(Createday) = "+ year;
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              items.add(Double.parseDouble(resultSet.getString(1)));
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
}
