package com.bcl.bexiapp_i_banking.AG_CUSTOMER;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDataModel {



    public String custId;

    public String custGender;

    public String custDob;

    public String custName;

    public GetDataModel(String custId, String custGender, String custDob, String custName) {
        this.custId = custId;
        this.custGender = custGender;
        this.custDob = custDob;
        this.custName = custName;
    }
}
