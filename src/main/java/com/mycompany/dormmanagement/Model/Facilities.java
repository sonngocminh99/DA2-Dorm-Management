/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dormmanagement.Model;

/**
 *
 * @author Mayy
 */
public class Facilities {
    protected String facilitiesID;
    protected String roomID;
    protected String apartmentID;
    protected String status;
    protected String name;
    protected int amount;
    protected String discripition;

    public Facilities(String facilitiesID, String roomID, String apartmentID, String status, String name, int amount, String discripition) {
        this.facilitiesID = facilitiesID;
        this.roomID = roomID;
        this.apartmentID = apartmentID;
        this.status = status;
        this.name = name;
        this.amount = amount;
        this.discripition = discripition;
    }
    public Facilities() {
        this.facilitiesID = "";
        this.roomID = "";
        this.apartmentID = "";
        this.status = "";
        this.name = "";
        this.amount = 0;
        this.discripition = "";
    }

    public String getFacilitiesID() {
        return facilitiesID;
    }

    public void setFacilitiesID(String facilitiesID) {
        this.facilitiesID = facilitiesID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDiscripition() {
        return discripition;
    }

    public void setDiscripition(String discripition) {
        this.discripition = discripition;
    }
    
    
}
