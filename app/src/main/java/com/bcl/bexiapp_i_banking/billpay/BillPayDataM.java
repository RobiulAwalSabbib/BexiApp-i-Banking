package com.bcl.bexiapp_i_banking.billpay;

public class BillPayDataM {

    private String pErrorFlag;
    private String pErrorMessage;

    public BillPayDataM(String pErrorFlag, String pErrorMessage) {
        this.pErrorFlag = pErrorFlag;
        this.pErrorMessage = pErrorMessage;
    }

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

