/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import connect.DataConnection;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
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

/**
 *
 * @author Mayy
 */
public class Student {
    private String studentID;
    private String fullName;
    private Date birthday;
    private String gender;
    private String idCard;
    private String phoneNum;
    private String university;
    private String grade;
    private String status;
    private String syear;
    private String eyear;
    private String idRoom;
    private String active;
    private InputStream image;


    public Student() {
        this.studentID = "";
        this.fullName = "";
        this.birthday = null;
        this.gender = "";
        this.idCard = "";
        this.phoneNum = "";
        this.university = "";
        this.grade = "";
        this.status = "";
        this.syear = "";
        this.eyear = "";
        this.idRoom = "";
        this.image = null;
        this.active = "";
    }

    

    
    public Student(String studentID, String fullName, Date birthday, String Gender, String IDCard, String phoneNum, String university, String grade, String status, String Syear, String Eyear, String idRoom, InputStream image) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = Gender;
        this.idCard = IDCard;
        this.phoneNum = phoneNum;
        this.university = university;
        this.grade = grade;
        this.status = status;
        this.syear = Syear;
        this.eyear = Eyear;
        this.idRoom = idRoom;
        this.image = image;
        this.active = "yes";
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String Gender) {
        this.gender = Gender;
    }

    public String getIDCard() {
        return idCard;
    }

    public void setIDCard(String IDCard) {
        this.idCard = IDCard;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public String getSyear() {
        return syear;
    }

    public String getEyear() {
        return eyear;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSyear(String Syear) {
        this.syear = Syear;
    }

    public void setEyear(String Eyear) {
        this.eyear = Eyear;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }


    public String getIdRoom() {
        return idRoom;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
    
    
    public ObservableList<Map<String, Object>> getStudent(int option){
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
                    query = "Select * from student where active = 'yes'";
                    break;
                case 2:
                    query = "Select * from student where status = 'ĐX' and active = 'yes'";
                    break;
                case 3:
                    query = "Select * from student where status = 'CX' and active = 'yes'";
                    break;
                default:
                    query = "Select * from student where active = 'yes'";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("name", resultSet.getString(2));
            item.put("gender", resultSet.getString(4));
            item.put("status", resultSet.getString(9));
            item.put("university", resultSet.getString(7));
            item.put("sYear", resultSet.getString(10));
            item.put("eYear", resultSet.getString(11));
            item.put("idRoom", resultSet.getString(12));
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
    
    
    public ObservableList<Map<String, Object>> getSearchStudent(int option, String keyWord){
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
                    query = "Select * from student where active = 'yes' and (IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%')";
                    break;
                case 2:
                    query = "Select * from student where active = 'yes' and status = 'ĐX' and (IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%')";
                    break;
                case 3:
                    query = "Select * from student where active = 'yes' and status = 'CX' and (IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%')";
                    break;
                default:
                    query = "Select * from student where active = 'yes' and (IDStudent LIKE '%" + keyWord +"%' or Fullname LIKE '%"+keyWord+"%')";
                    break;
            }
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
            item = new HashMap<>();
            item.put("id", resultSet.getString(1));
            item.put("name", resultSet.getString(2));
            item.put("gender", resultSet.getString(4));
            item.put("status", resultSet.getString(9));
            item.put("university", resultSet.getString(7));
            item.put("sYear", resultSet.getString(10));
            item.put("eYear", resultSet.getString(11));
            item.put("idRoom", resultSet.getString(12));
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
    

    public ArrayList<String> getAllStudent(){
    
        ArrayList<String> listStudent = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select Fullname from student where active = 'yes'";
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
    
    public ArrayList<String> getAllEmptyStudent(){
    
        ArrayList<String> listStudent = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select Fullname from student where status = 'CX' and active = 'yes'";
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
    
    public int getLastStudentIndex(){
        String lastStudent = "";
        int index = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select IDStudent from student" ;
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                lastStudent=resultSet.getString(1);             
                int tempindex = Integer.parseInt(lastStudent.substring(2));
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
        return index;
    }
    
    public int getTotalStudents(){
        int total = 0;
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query ="select IDStudent from student where active = 'yes'" ;
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
    

    public void getInfo(String student){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();

            String query = "Select * from student where active = 'yes' and Fullname ='"+student+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.studentID = resultSet.getString(1);
              this.fullName = resultSet.getString(2);
              this.birthday = resultSet.getDate(3);
              this.gender = resultSet.getString(4);
              this.idCard = resultSet.getString(5);
              this.phoneNum = resultSet.getString(6);
              this.university = resultSet.getString(7);
              this.grade = resultSet.getString(8);
              this.status = resultSet.getString(9);
              this.syear = resultSet.getString(10);
              this.eyear = resultSet.getString(11);
              this.idRoom = resultSet.getString(12);

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

    
    public void getInfoByID(String studentID){
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select * from student where active = 'yes' and IDStudent ='"+studentID+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              this.studentID = resultSet.getString(1);
              this.fullName = resultSet.getString(2);
              this.birthday = resultSet.getDate(3);
              this.gender = resultSet.getString(4);
              this.idCard = resultSet.getString(5);
              this.phoneNum = resultSet.getString(6);
              this.university = resultSet.getString(7);
              this.grade = resultSet.getString(8);
              this.status = resultSet.getString(9);
              this.syear = resultSet.getString(10);
              this.eyear = resultSet.getString(11);
              this.idRoom = resultSet.getString(12);
              this.image = resultSet.getBinaryStream(13);
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
        System.out.println("success2");        
    }
    
    public void insertStudentdata(int length){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        
        try {  
            String query ="insert into student(IDStudent,Fullname,Birthday,Gender,IDCard,PhoneNumber,university,grade,status,sYear,eYear,IDRoom,Image,active)values(?,?,?,?,?,?,?,?,?,?,?,?,?,'yes')" ;
            statement = con.prepareStatement(query);
            statement.setString(1, this.studentID);
            statement.setString(2, this.fullName);
            statement.setDate(3, this.birthday);
            statement.setString(4, this.gender);
            statement.setString(5, this.idCard);
            statement.setString(6, this.phoneNum);
            statement.setString(7, this.university);
            statement.setString(8, this.grade);
            statement.setString(9, this.status);
            statement.setString(10, this.syear);
            statement.setString(11, this.eyear);
            statement.setString(12, this.idRoom);
            statement.setBinaryStream(13, this.image, length);
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
    
    public void updateRoom(String idRoom) {
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update student set status = 'ĐX', IDRoom = '"+idRoom+"' where IDStudent ='"+this.studentID+"'" ;
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
    
    public void updateStudent(String studentID, String fullName, Date birthday, String Gender, String IDCard, String phoneNum, String university, String grade, String status, String Syear, String Eyear, String idRoom, InputStream image, int length ){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            if(idRoom == null){
                String query ="update student set Fullname = '"+fullName+"', Birthday = '"+birthday+"',Gender = '"+Gender+"', IDCard = '"+IDCard+"',phoneNumber = '"+phoneNum+"', University = '"+university+"',Grade = '"+grade+"', status = '"+status+"',Syear = '"+Syear+"', Eyear = '"+Eyear+"', Image=? where active = 'yes' and IDStudent ='"+studentID+"'" ;
                statement = con.prepareStatement(query);
                statement.setBinaryStream(1, image, length);
                statement.execute();
            } else {
                String query ="update student set Fullname = '"+fullName+"', Birthday = '"+birthday+"',Gender = '"+Gender+"', IDCard = '"+IDCard+"',phoneNumber = '"+phoneNum+"', University = '"+university+"',Grade = '"+grade+"', status = '"+status+"',Syear = '"+Syear+"', Eyear = '"+Eyear+"', IDRoom = '"+idRoom+"', Image=? where active = 'yes' and IDStudent ='"+studentID+"'" ;
                statement = con.prepareStatement(query);
                statement.setBinaryStream(1, image, length);
                statement.execute();
            }
           
            
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
    
    public void deleteData(String student ){
        
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update student set active = 'no' where IDStudent ='"+student+"'" ;
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
    public void updateStudentRemoveFromRoom(String id){
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update student set IDRoom=null, status = 'CX' where active = 'yes' and IDRoom='"+id+"'";
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
    public void RemoveStudent(String id){
        Connection con = DataConnection.getConnection(); 
        PreparedStatement statement = null;
        try {  
            String query ="update student set IDRoom=null, status = 'CX' where active = 'yes' and IDStudent='"+id+"'";
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
    public ArrayList<String> getIDEmptyStudent(){
    
        ArrayList<String> listStudent = new ArrayList<String>();
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select IDStudent from student where active = 'yes' and status = 'CX'";
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
    public String getToIDRoomStudent(String idstudent){
        String idroom = "";
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select student.IDRoom from quanlyktx.student where student.active = 'yes' and student.IDStudent ='"+idstudent+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              idroom = resultSet.getString(1);
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
        return idroom;
    }
    
    public String getToNameStudent(String idstudent){
        String idroom = "";
        Connection con = DataConnection.getConnection(); 
        Statement statement = null;
        ResultSet resultSet = null;
        try {        
            statement = con.createStatement();
            String query = "Select student.Fullname from quanlyktx.student where student.active = 'yes' and student.IDStudent ='"+idstudent+"'";
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
              idroom = resultSet.getString(1);
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
        return idroom;
    }
    
   
}
