package com.bcl.bexiapp_i_banking.TranDetail_Get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementDataModel {


    public String transactionAmount;

    public String acnumber;

    public String mot;

    public String destinationAc;

    public String dot;

    public String tnumber;

    public String transactionType;

    public String openingBalance;

    public StatementDataModel(String transactionAmount, String acnumber, String mot, String destinationAc, String dot, String tnumber, String transactionType, String openingBalance) {
        this.transactionAmount = transactionAmount;
        this.acnumber = acnumber;
        this.mot = mot;
        this.destinationAc = destinationAc;
        this.dot = dot;
        this.tnumber = tnumber;
        this.transactionType = transactionType;
        this.openingBalance = openingBalance;
    }
}
