package com.bcl.bexiapp_i_banking.Transaction_Medium_Get_All_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction_Medium_Get_All_Data_Return_M {
    @SerializedName("tm_id")
    @Expose
    private String tmId;
    @SerializedName("tm_status")
    @Expose
    private String tmStatus;
    @SerializedName("tm_desc")
    @Expose
    private String tmDesc;

    public String getTmId() {
        return tmId;
    }

    public void setTmId(String tmId) {
        this.tmId = tmId;
    }

    public String getTmStatus() {
        return tmStatus;
    }

    public void setTmStatus(String tmStatus) {
        this.tmStatus = tmStatus;
    }

    public String getTmDesc() {
        return tmDesc;
    }

    public void setTmDesc(String tmDesc) {
        this.tmDesc = tmDesc;
    }
}
