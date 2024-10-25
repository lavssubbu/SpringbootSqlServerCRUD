package com.example.Java.Springboot.SqlServer;

public class Employee{
    int Empid;
    String Empname;
    String city;

    // Getters and setters
    public int getEmpid() {
        return Empid;
    }
    public void setEmpid(int empid) {
        this.Empid = empid;
    }
    public String getEmpname() {
        return Empname;
    }
    public void setEmpname(String empname) {
        this.Empname = empname;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    @Override
    public String toString()
    {
       return "EmployeeID: " + Empid + " | Empname: " + Empname + " | City: " + city ;
    }
}
