package com.bcl.bexiapp_i_banking.billpay;

import com.google.gson.annotations.SerializedName;

public class BillPayRecM {

    @SerializedName("pErrorFlag")
    public String pErrorFlag;

    @SerializedName("pErrorMessage")
    public String pErrorMessage;

    public String getpErrorFlag() {
        return pErrorFlag;
    }

    public void setpErrorFlag(String pErrorFlag) {
        this.pErrorFlag = pErrorFlag;
    }

    public String getpErrorMessage() {
        return pErrorMessage;
    }

    public void setpErrorMessage(String pErrorMessage) {
        this.pErrorMessage = pErrorMessage;
    }

}

