package com.bcl.bexiapp_i_banking.db;

import java.util.ArrayList;

public class UserM {

    private String id, name, mobile, email, dob;

    public UserM(String id, String name, String mobile, String email, String dob) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.dob = dob;
    }

    public UserM() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
