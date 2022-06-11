/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

/**
 *
 * @author Mayy
 */
public class Contract {
    protected String IDContract;
    protected String IDRoom;
    protected String IDApartment;
    protected String IDEmployee;
    protected String IDStudent;
    protected String Createday;
    protected String Startday;
    protected String Endday;

    public Contract(String IDContract, String IDRoom, String IDApartment, String IDEmployee, String IDStudent, String Createday, String Startday, String Endday) {
        this.IDContract = IDContract;
        this.IDRoom = IDRoom;
        this.IDApartment = IDApartment;
        this.IDEmployee = IDEmployee;
        this.IDStudent = IDStudent;
        this.Createday = Createday;
        this.Startday = Startday;
        this.Endday = Endday;
    }
    
    public Contract() {
        this.IDContract = "";
        this.IDRoom = "";
        this.IDApartment = "";
        this.IDEmployee = "";
        this.IDStudent = "";
        this.Createday = "";
        this.Startday = "";
        this.Endday = "";
    }

    public String getIDContract() {
        return IDContract;
    }

    public void setIDContract(String IDContract) {
        this.IDContract = IDContract;
    }

    public String getIDRoom() {
        return IDRoom;
    }

    public void setIDRoom(String IDRoom) {
        this.IDRoom = IDRoom;
    }

    public String getIDApartment() {
        return IDApartment;
    }

    public void setIDApartment(String IDApartment) {
        this.IDApartment = IDApartment;
    }

    public String getIDEmployee() {
        return IDEmployee;
    }

    public void setIDEmployee(String IDEmployee) {
        this.IDEmployee = IDEmployee;
    }

    public String getIDStudent() {
        return IDStudent;
    }

    public void setIDStudent(String IDStudent) {
        this.IDStudent = IDStudent;
    }

    public String getCreateday() {
        return Createday;
    }

    public void setCreateday(String Createday) {
        this.Createday = Createday;
    }

    public String getStartday() {
        return Startday;
    }

    public void setStartday(String Startday) {
        this.Startday = Startday;
    }

    public String getEndday() {
        return Endday;
    }

    public void setEndday(String Endday) {
        this.Endday = Endday;
    }
    
}
