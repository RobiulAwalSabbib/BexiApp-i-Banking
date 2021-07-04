package com.bcl.bexiapp_i_banking.Benificier_Single_Account_Get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiveModel {

    @SerializedName("ACC_TITLE")
    @Expose
    private String accTitle;
    @SerializedName("BENI_ACC")
    @Expose
    private String beniAcc;
    @SerializedName("REF_ACC")
    @Expose
    private String refAcc;
    @SerializedName("SLNO")
    @Expose
    private String slno;
    @SerializedName("ACC_TYPE")
    @Expose
    private String accType;

    public String getAccTitle() {
        return accTitle;
    }

    public void setAccTitle(String accTitle) {
        this.accTitle = accTitle;
    }

    public String getBeniAcc() {
        return beniAcc;
    }

    public void setBeniAcc(String beniAcc) {
        this.beniAcc = beniAcc;
    }

    public String getRefAcc() {
        return refAcc;
    }

    public void setRefAcc(String refAcc) {
        this.refAcc = refAcc;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }


}
