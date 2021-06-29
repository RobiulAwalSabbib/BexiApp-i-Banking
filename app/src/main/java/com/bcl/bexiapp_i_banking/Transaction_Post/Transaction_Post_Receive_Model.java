package com.bcl.bexiapp_i_banking.Transaction_Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction_Post_Receive_Model {

    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("error_flag")
    @Expose
    private String errorFlag;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(String errorFlag) {
        this.errorFlag = errorFlag;
    }

}
