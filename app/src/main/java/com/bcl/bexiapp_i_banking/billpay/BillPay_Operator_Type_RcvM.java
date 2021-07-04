package com.bcl.bexiapp_i_banking.billpay;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillPay_Operator_Type_RcvM {

    @SerializedName("TYPE_CODE")
    @Expose
    private String typeCode;
    @SerializedName("TYPE_NAME")
    @Expose
    private String typeName;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
