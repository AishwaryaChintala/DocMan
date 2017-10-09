package com.example.aishu.docman;

/**
 * Created by aishu on 01/10/17.
 */

public class User {
    private String Docname="";
    private String DocLoc="";

    public User()
    {
    }
    public String getDocname() {
        return Docname;
    }
    public void setDocname(String Docname) {
        this.Docname = Docname;
    }
    public String getDocLoc() {
        return DocLoc;
    }
    public void setDocLoc(String DocLoc) {
        this.DocLoc = DocLoc;
    }

}
