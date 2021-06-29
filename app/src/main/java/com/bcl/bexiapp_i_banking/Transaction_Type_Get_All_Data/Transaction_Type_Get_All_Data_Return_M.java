package com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction_Type_Get_All_Data_Return_M {
    @SerializedName("tt_id")
    @Expose
    private String ttId;
    @SerializedName("tt_status")
    @Expose
    private String ttStatus;
    @SerializedName("tt_desc")
    @Expose
    private String ttDesc;

    public String getTtId() {
        return ttId;
    }

    public void setTtId(String ttId) {
        this.ttId = ttId;
    }

    public String getTtStatus() {
        return ttStatus;
    }

    public void setTtStatus(String ttStatus) {
        this.ttStatus = ttStatus;
    }

    public String getTtDesc() {
        return ttDesc;
    }

    public void setTtDesc(String ttDesc) {
        this.ttDesc = ttDesc;
    }
}
