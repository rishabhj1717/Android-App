package com.example.android.myapplication;

/**
 * Created by mahak on 20/9/17.
 */

public class StudentInfo {

     private String name;
     private String cls;
     private String email;

    public StudentInfo()
    {

    }

    public StudentInfo(String n, String c, String e)
    {
        name=n;
        cls=c;
        email= e;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClS() {
        return cls;
    }

    public void setClS(String clS) {
        this.cls = clS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
