package com.example.aishu.docman;

/**
 * Created by aishu on 02/10/17.
 */

public class Blog {
    private String Name;
    private String title;

    public Blog(){

    }


    public Blog(String name, String title) {
        Name = name;
        this.title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
