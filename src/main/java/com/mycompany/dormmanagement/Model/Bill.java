/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Mayy
 */
public class Bill {
   protected String  billID;
   protected Employee employee;
   protected Apartment apartment;
   protected Room room;
   protected Date createDay;
   protected String total;
   protected String status;
   protected NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));

    public Bill(String BillID, Employee employee, Apartment apartment, Room room, Date createDay, String total,String status) {
        this.billID = BillID;
        this.employee = employee;
        this.apartment = apartment;
        this.room = room;
        this.createDay = createDay;
        this.total = total;
        this.status = status;
    }

    public Bill() {
        this.billID = "";
        this.employee = new Employee();
        this.apartment = new Apartment();
        this.room = new Room();
        this.createDay =  new Date();
        this.total = "0";
        this.status = "Chưa thu";
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String BillID) {
        this.billID = BillID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public String getStatus(){
        return status;
    }
   
    public void setStatus(String status){
        this.status=status;
    }
}

