package com.bcl.bexiapp_i_banking.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataM {
    @SerializedName("pErrorFlag")
    @Expose
    private String pErrorFlag;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("pErrorMessage")
    @Expose
    private String pErrorMessage;
    @SerializedName("acNo")
    @Expose
    private String acNo;

    public String getpErrorFlag() {
        return pErrorFlag;
    }

    public void setpErrorFlag(String pErrorFlag) {
        this.pErrorFlag = pErrorFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getpErrorMessage() {
        return pErrorMessage;
    }

    public void setpErrorMessage(String pErrorMessage) {
        this.pErrorMessage = pErrorMessage;
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }
    
}
