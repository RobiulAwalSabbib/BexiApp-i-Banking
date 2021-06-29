package com.bcl.bexiapp_i_banking.AG_CUSTOMER;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AGCustomerGetAllDataM {

    @SerializedName("CUST_ID")
    @Expose
    private String custId;
    @SerializedName("CUST_GENDER")
    @Expose
    private String custGender;
    @SerializedName("CUST_DOB")
    @Expose
    private String custDob;
    @SerializedName("CUST_NAME")
    @Expose
    private String custName;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustGender() {
        return custGender;
    }

    public void setCustGender(String custGender) {
        this.custGender = custGender;
    }

    public String getCustDob() {
        return custDob;
    }

    public void setCustDob(String custDob) {
        this.custDob = custDob;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }



}
