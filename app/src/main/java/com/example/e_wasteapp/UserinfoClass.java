package com.example.e_wasteapp;

public class UserinfoClass {

    String user,pass,fname,lname,phno;

    public UserinfoClass() {
    }

    public UserinfoClass(String user, String pass, String fname, String lname, String phno) {
        this.user=user;
        this.pass=pass;
        this.fname=fname;
        this.lname=lname;
        this.phno=phno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
