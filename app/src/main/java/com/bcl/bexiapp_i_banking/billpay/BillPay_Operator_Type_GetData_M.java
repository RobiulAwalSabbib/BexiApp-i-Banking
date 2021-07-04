package com.bcl.bexiapp_i_banking.billpay;

public class BillPay_Operator_Type_GetData_M {

    private String TYPE_CODE,
            TYPE_NAME;

    public BillPay_Operator_Type_GetData_M(String TYPE_CODE, String TYPE_NAME) {
        this.TYPE_CODE = TYPE_CODE;
        this.TYPE_NAME = TYPE_NAME;
    }

    public String getTYPE_CODE() {
        return TYPE_CODE;
    }

    public void setTYPE_CODE(String TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public String getTYPE_NAME() {
        return TYPE_NAME;
    }

    public void setTYPE_NAME(String TYPE_NAME) {
        this.TYPE_NAME = TYPE_NAME;
    }
}


