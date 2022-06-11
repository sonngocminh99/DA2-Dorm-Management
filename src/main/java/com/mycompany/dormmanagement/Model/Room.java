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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Mayy
 */
public class Room {
protected String roomID;
protected Apartment apartment;
protected String noStudent;
protected String status;
protected String type;
protected int rentingPrice;



    public Room() {
        this.roomID = "";
        this.apartment = new Apartment();
        this.noStudent = "";
        this.status = "";
        this.type = "";
        this.rentingPrice = 0;
        
    }

    public Room(String roomID, Apartment apartment, String noStudent, String status, String type, int rentingPrice) {
        this.roomID = roomID;
        this.apartment = apartment;
        this.noStudent = noStudent;
        this.status = status;
        this.type = type;
        this.rentingPrice = rentingPrice;
       
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getNoStudent() {
        return noStudent;
    }

    public void setNoStudent(String noStudent) {
        this.noStudent = noStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRentingPrice() {
        return rentingPrice;
    }

    public void setRentingPrice(int rentingPrice) {
        this.rentingPrice = rentingPrice;
    }
    //lấy dữ liệu phòng từ IDApartment 
    public ObservableList<Map<String, Object>> getRoom(String apartment, int option){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        
        try {        
            statement = con.createStatement();
            String query ="" ;
            //dựa vào tham số option truyền vào mà lấy dữ liệu tùy vào giá trị option
            switch (option) {
                case 1:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and active='yes'";
                    break;
                case 2:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Còn chỗ' and active='yes'";
                    break;
                case 3:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Đầy' and active='yes'";
                    break;
                default:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and active='yes'";
                    break;
            }
            //thực hiện câu query và lưu kết quả vào items
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("idroom", resultSet.getString(1));
            item.put("nostudent", resultSet.getString(3));
            item.put("status", resultSet.getString(4));
            item.put("type", "Phòng " + resultSet.getString(5));
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
    
    //lấy dữ liệu phòng dừa vào keyWord search người dùng nhập
public ObservableList<Map<String, Object>> getSearchRoom(String apartment, int option, String keyWord){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        //khởi tạo chuỗi query dựa vào 2 tham số option và keyword 
        try {        
            statement = con.createStatement();
            String query ="" ;
            switch (option) {
                case 1:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and IDRoom LIKE '%" + keyWord +"%' and active='yes'";
                    break;
                case 2:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Còn chỗ' and IDRoom LIKE '%" + keyWord +"%' and active='yes'";
                    break;
                case 3:
                    query = "Select * from room where IDApartment ='"+ apartment+"' and status = 'Đầy' and IDRoom LIKE '%" + keyWord +"%' and active='yes'";
                    break;
                default:
                    query = "Select * from room where IDApartment ='"+ apartment+"'and IDRoom LIKE '%" + keyWord +"%' and active='yes'";
                    break;
            }
            //thực hiện câu query và lưu kết quả vào items
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("idroom", resultSet.getString(1));
            item.put("nostudent", resultSet.getString(3));
            item.put("status", resultSet.getString(4));
            item.put("type","Phòng " + resultSet.getString(5));
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
    
    // lấy dữ liệu tất cả các phòng trống
    public ArrayList<String> getRoomAvailable(String apartment){
        ArrayList<String> room = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        //khởi tạo chuỗi query theo tham số idApartment
        try {        
            statement = con.createStatement();

            String query = "Select IDRoom from room where IDApartment ='"+ apartment+"' and status = 'Còn chỗ'";
            //thực hiện câu query và lưu kết quả vào room
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              room.add(resultSet.getString(1));
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
        return room;
    }
    // lấy dữ liệu phòng trống với giới tính
    public String getRoomAvailableWithGender(String gender){
        String room = null;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            //khởi tạo chuỗi query theo tham số gender
            String query = "Select room.IDRoom from room, apartment where room.IDApartment = apartment.IDApartment and apartment.gender = '"+gender+"' and Status = 'Còn chỗ' and room.active='yes' ORDER BY NoStudent DESC LIMIT 1";
            //thực hiện câu query và lưu kết quả vào room
            resultSet = statement.executeQuery(query);
            if (resultSet.next())
                room = resultSet.getString(1);
            else
                room = null;
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
        
        return room;
    }
    // lấy dữ liệu phòng bằng IDRoom
    public void getInfo(String room){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            //khởi tạo chuỗi query theo tham số room
            String query = "Select * from room where IDRoom ='"+room+"'";
            //thực hiện câu query và lưu kết quả 
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.roomID = resultSet.getString(1);
              this.apartment.getInfo(resultSet.getString(2));
              this.noStudent = resultSet.getString(3);
              this.status = resultSet.getString(4);
              this.type = resultSet.getString(5);
              this.rentingPrice = resultSet.getInt(6);
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
    //đếm tổng số lượng phòng
    public int getTotalRooms(){
        int total = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            // thực hiện câu query lấy tất cả IDRoom và với mỗi IDRoom tiên hành đếm tổng số lượng
            statement = con.createStatement();
            String query ="select IDRoom from room where active='yes'" ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){           
                total++;
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
        return total;
    }
    //thêm thông tin số lượng sinh viên của phòng
public void insertdatatoType(String room,String type, int rentingprice ){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update room set Type = '"+type+"', RentingPrice = '"+rentingprice+"' where IDRoom ='"+room+"'" ;
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
// thêm dữ liệu phòng mới
public void insertRoomdata(){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="insert into room(IDRoom,IDApartment,NoStudent,Status,Type,RentingPrice,active)values(?,?,?,?,?,?,'yes')" ;
            statement = con.prepareStatement(query);
            statement.setString(1, this.roomID);
            statement.setString(2, this.apartment.getIDApartment());
            statement.setString(3, this.noStudent);
            statement.setString(4, this.status);
            statement.setString(5, this.type);
            statement.setInt(6, this.rentingPrice);
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

//lấy số thứ tự phòng cuối cùng của bảng dữ liệu
public int getLastRoomIndex(String apartment ){
        String lastRoom = "";
        int index = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select IDRoom from quanlyktx.room where IDApartment ='"+apartment+"'" ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                lastRoom=resultSet.getString(1);             
                int tempindex = Integer.parseInt(lastRoom.substring(1));
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
        if(index==0){index = 100;}
        System.out.println(index);
        return index;
    }

//xóa dữ liệu trong database dựa vào IDRoom
public void deleteData(String room ){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update quanlyktx.room set active='no' where IDRoom ='"+room+"'" ;
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

public void deleteAllRoomInApartment(String apartment){
 Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update room set active='no' where IDApartment = '"+apartment+"'" ;
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
public double getLastRentingPiceRoom(String room){
        double retingpice = 0.0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="Select RentingPrice from quanlyktx.room where  room.IDRoom = '"+room+"' " ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                retingpice = resultSet.getDouble(1);
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
        return retingpice;
}

    public void addStudentToRoom() {
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        int newNoStudent = Integer.parseInt(this.noStudent) + 1;
        if (newNoStudent >= Integer.parseInt(this.type)) {
            try {  
                String query ="update room set status = 'Hết chỗ', NoStudent = '"+String.valueOf(newNoStudent)+"' where IDRoom ='"+this.roomID+"'" ;
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
            
        } else {
            try {  
                String query ="update room set status = 'Còn chỗ', NoStudent = '"+String.valueOf(newNoStudent)+"' where IDRoom ='"+this.roomID+"'" ;
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
    }
    
    public void removeStudentFromRoom() {
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        int newNoStudent = Integer.parseInt(this.noStudent) - 1;
        System.out.println("success: " + newNoStudent);
        
            try {  
                String query ="update room set status = 'Còn chỗ', NoStudent = '"+String.valueOf(newNoStudent)+"' where IDRoom ='"+this.roomID+"'" ;
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
public ObservableList<Map<String, Object>> getStudentRoom(String room, int option){
        ObservableList<Map<String, Object>> items =
        FXCollections.<Map<String, Object>>observableArrayList();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        Map<String, Object> item;
        try {        
            statement = con.createStatement();
            String query = "Select student.IDStudent, student.Fullname, student.Gender,student.University from quanlyktx.student,quanlyktx.room where student.IDRoom = room.IDRoom and room.IDRoom = '"+ room+"' and student.active='yes'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("IDStudent", resultSet.getString(1));
            item.put("Fullname", resultSet.getString(2));
            item.put("Gender", resultSet.getString(3));
            item.put("University", resultSet.getString(4));
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
public ArrayList<String> getRoomNameBaseStudent(String idroom){
    ArrayList<String> listStudent = new ArrayList<>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select student.IDStudent from quanlyktx.room, quanlyktx.student where room.IDRoom = student.IDRoom and room.IDRoom = '"+idroom+"' and room.active='yes'";
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
}
