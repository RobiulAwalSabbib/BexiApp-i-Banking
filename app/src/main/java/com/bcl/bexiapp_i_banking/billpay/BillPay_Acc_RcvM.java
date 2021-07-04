package com.bcl.bexiapp_i_banking.billpay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillPay_Acc_RcvM {

    @SerializedName("ATYPE")
    @Expose
    private String atype;
    @SerializedName("ACNUMBER")
    @Expose
    private String acnumber;
    @SerializedName("AOD")
    @Expose
    private String aod;
    @SerializedName("ASTATUS")
    @Expose
    private String astatus;
    @SerializedName("CUSTID")
    @Expose
    private String custid;
    @SerializedName("BID")
    @Expose
    private String bid;
    @SerializedName("OPENING_BALANCE")
    @Expose
    private String openingBalance;

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getAcnumber() {
        return acnumber;
    }

    public void setAcnumber(String acnumber) {
        this.acnumber = acnumber;
    }

    public String getAod() {
        return aod;
    }

    public void setAod(String aod) {
        this.aod = aod;
    }

    public String getAstatus() {
        return astatus;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }
}
