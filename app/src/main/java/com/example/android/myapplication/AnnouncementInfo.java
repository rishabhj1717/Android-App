package com.example.android.myapplication;

import java.io.Serializable;

/**
 * Created by mahak on 3/10/17.
 */

public class AnnouncementInfo implements Serializable {

    private String ann;
    private String tUid;
    private String date;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    private String sub;

    public AnnouncementInfo()
    {
        ann= "Default ann";
        tUid="Default tuid";
    }

    public AnnouncementInfo(String ann, String tUid, String date, String sub)
    {
        this.ann=ann;
        this.tUid=tUid;
        this.date=date;
        this.sub=sub;
    }


    public String getAnn() {
        return ann;
    }

    public void setAnn(String ann) {
        this.ann = ann;
    }

    public String gettUid() {
        return tUid;
    }

    public void settUid(String tUid) {
        this.tUid = tUid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
