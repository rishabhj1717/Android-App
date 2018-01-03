package com.example.android.myapplication;

import java.io.Serializable;

/**
 * Created by mahak on 20/9/17.
 */

public class QuestionInfo implements Serializable {

    private String q;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String sname;
    private String topic;

    public QuestionInfo()
    {
        a1="1";
        a2="2";
        a3="3";
        a4="4";

    }

    public QuestionInfo(String q, String a1, String a2, String a3, String a4, String name, String topic)
    {
        this.q=q;
        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        this.sname=name;
        this.topic=topic;
    }


    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String name) {
        this.sname = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }





//    public static ArrayList<QuestionInfo> createQuestionsList(int numContacts) {
//        ArrayList<QuestionInfo> contacts = new ArrayList<QuestionInfo>();
//
//        for (int i = 1; i <= numContacts; i++) {
//            contacts.add(new QuestionInfo());
//        }
//
//        return contacts;
//    }



}
