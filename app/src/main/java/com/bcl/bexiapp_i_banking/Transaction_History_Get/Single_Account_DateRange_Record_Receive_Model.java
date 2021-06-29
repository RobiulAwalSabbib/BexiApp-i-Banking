package com.bcl.bexiapp_i_banking.Transaction_History_Get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Single_Account_DateRange_Record_Receive_Model {

    @SerializedName("TO_ACC")
    @Expose
    private String toAcc;
    @SerializedName("SL_NO")
    @Expose
    private String slNo;
    @SerializedName("NEW_BALANCE")
    @Expose
    private String newBalance;
    @SerializedName("TRAN_NO")
    @Expose
    private Object tranNo;
    @SerializedName("FROM_ACC")
    @Expose
    private String fromAcc;
    @SerializedName("TRAN_AMOUNT")
    @Expose
    private String tranAmount;
    @SerializedName("OLD_BALANCE")
    @Expose
    private String oldBalance;
    @SerializedName("DAT")
    @Expose
    private String dat;
    @SerializedName("TRAN_MEDIUM")
    @Expose
    private String tranMedium;
    @SerializedName("TRAN_TYPE")
    @Expose
    private String tranType;

    public String getToAcc() {
        return toAcc;
    }

    public void setToAcc(String toAcc) {
        this.toAcc = toAcc;
    }

    public String getSlNo() {
        return slNo;
    }

    public void setSlNo(String slNo) {
        this.slNo = slNo;
    }

    public String getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }

    public Object getTranNo() {
        return tranNo;
    }

    public void setTranNo(Object tranNo) {
        this.tranNo = tranNo;
    }

    public String getFromAcc() {
        return fromAcc;
    }

    public void setFromAcc(String fromAcc) {
        this.fromAcc = fromAcc;
    }

    public String getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(String tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(String oldBalance) {
        this.oldBalance = oldBalance;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getTranMedium() {
        return tranMedium;
    }

    public void setTranMedium(String tranMedium) {
        this.tranMedium = tranMedium;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

}
