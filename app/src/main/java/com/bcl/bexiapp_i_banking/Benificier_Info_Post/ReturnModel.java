package com.bcl.bexiapp_i_banking.Benificier_Info_Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnModel {

    @SerializedName("pErrorFlag")
    @Expose
    public String pErrorFlag;

    @SerializedName("pErrorMessage")
    @Expose
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
