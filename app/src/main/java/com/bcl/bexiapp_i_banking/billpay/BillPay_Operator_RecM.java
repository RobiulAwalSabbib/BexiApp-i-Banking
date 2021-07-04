package com.bcl.bexiapp_i_banking.billpay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillPay_Operator_RecM {

    @SerializedName("OPER_NAME")
    @Expose
    private String operName;
    @SerializedName("OPER_CODE")
    @Expose
    private String operCode;

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }
}
