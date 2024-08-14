package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders 
{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    String coursename;
    String status="Pending";
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCoursename() {
        return coursename;
    }
    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
