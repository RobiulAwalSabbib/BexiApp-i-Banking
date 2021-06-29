package com.bcl.bexiapp_i_banking.TopUp_Request_Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recharge_Request_Post_Receive_Data_Model {

    @SerializedName("pErrorFlag")
    @Expose
    private String pErrorFlag;
    @SerializedName("pErrorMessage")
    @Expose
    private String pErrorMessage;

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
