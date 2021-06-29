package com.bcl.bexiapp_i_banking.Benificier_Info_Post;

public class RequestModel {

    private String cust_id,acc_no,acc_type,acc_title;

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getAcc_title() {
        return acc_title;
    }

    public void setAcc_title(String acc_title) {
        this.acc_title = acc_title;
    }
}
