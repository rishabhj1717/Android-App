package com.example.android.myapplication;

/**
 * Created by mahak on 27/9/17.
 */

public class Upload {

    public String name;
    public String url;
    public String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url, String sub) {
        this.name = name;
        this.url = url;
        this.sub=sub;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }


}
