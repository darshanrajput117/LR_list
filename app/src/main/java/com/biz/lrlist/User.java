package com.biz.lrlist;

public class User {
    String fname;
    String lname;
    String mobileno;
    String emailid;

    public  User(String fname,String lname,String mobileno,String emailid){
        this.fname=fname;
        this.lname=lname;
        this.mobileno=mobileno;
        this.emailid=emailid;
    }
    public String getFName() {
        return fname;
    }

    public void setFName(String name) {
        this.fname = fname;
    }

    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }
    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
