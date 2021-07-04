package com.bcl.bexiapp_i_banking.billpay;

public class BillPay_Operator_GetData_M {

    private String OPER_CODE,
            OPER_NAME;

    public BillPay_Operator_GetData_M(String OPER_CODE, String OPER_NAME) {
        this.OPER_CODE = OPER_CODE;
        this.OPER_NAME = OPER_NAME;
    }

    public String getOPER_CODE() {
        return OPER_CODE;
    }

    public void setOPER_CODE(String OPER_CODE) {
        this.OPER_CODE = OPER_CODE;
    }

    public String getOPER_NAME() {
        return OPER_NAME;
    }

    public void setOPER_NAME(String OPER_NAME) {
        this.OPER_NAME = OPER_NAME;
    }
}

