package com.bcl.bexiapp_i_banking.TopUp_Request_Post;

public class Recharge_Request_Post_Request_Data_Model {

    private String MOBILE,OPERATORS_CODE,SIM_TYPE_NAME,AMOUNT,RECHARGE_CODE;

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getOPERATORS_CODE() {
        return OPERATORS_CODE;
    }

    public void setOPERATORS_CODE(String OPERATORS_CODE) {
        this.OPERATORS_CODE = OPERATORS_CODE;
    }

    public String getSIM_TYPE_NAME() {
        return SIM_TYPE_NAME;
    }

    public void setSIM_TYPE_NAME(String SIM_TYPE_NAME) {
        this.SIM_TYPE_NAME = SIM_TYPE_NAME;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getRECHARGE_CODE() {
        return RECHARGE_CODE;
    }

    public void setRECHARGE_CODE(String RECHARGE_CODE) {
        this.RECHARGE_CODE = RECHARGE_CODE;
    }
}
