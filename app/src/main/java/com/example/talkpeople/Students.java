package com.example.talkpeople;

public class Students {

    String dname;
    String demail;
    String dpassword;
    String dnumber;

    public Students(String dname, String demail, String dpassword, String dnumber) {
        this.dname = dname;
        this.demail = demail;
        this.dpassword = dpassword;
        this.dnumber = dnumber;
    }

    public String getDname() {
        return dname;
    }

    public String getDemail() {
        return demail;
    }

    public String getDpassword() {
        return dpassword;
    }

    public String getDnumber() {
        return dnumber;
    }
}
