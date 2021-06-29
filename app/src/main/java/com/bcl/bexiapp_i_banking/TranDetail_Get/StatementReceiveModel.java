package com.bcl.bexiapp_i_banking.TranDetail_Get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementReceiveModel {


    @SerializedName("TRANSACTION_AMOUNT")
    @Expose
    private String transactionAmount;
    @SerializedName("ACNUMBER")
    @Expose
    private String acnumber;
    @SerializedName("MOT")
    @Expose
    private String mot;
    @SerializedName("DESTINATION_AC")
    @Expose
    private String destinationAc;
    @SerializedName("DOT")
    @Expose
    private String dot;
    @SerializedName("TNUMBER")
    @Expose
    private String tnumber;
    @SerializedName("TRANSACTION_TYPE")
    @Expose
    private String transactionType;
    @SerializedName("OPENING_BALANCE")
    @Expose
    private String openingBalance;

    public StatementReceiveModel(String transactionAmount, String acnumber, String mot, String destinationAc, String dot, String tnumber, String transactionType, String openingBalance) {
        this.transactionAmount = transactionAmount;
        this.acnumber = acnumber;
        this.mot = mot;
        this.destinationAc = destinationAc;
        this.dot = dot;
        this.tnumber = tnumber;
        this.transactionType = transactionType;
        this.openingBalance = openingBalance;
    }


    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getAcnumber() {
        return acnumber;
    }

    public void setAcnumber(String acnumber) {
        this.acnumber = acnumber;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getDestinationAc() {
        return destinationAc;
    }

    public void setDestinationAc(String destinationAc) {
        this.destinationAc = destinationAc;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public String getTnumber() {
        return tnumber;
    }

    public void setTnumber(String tnumber) {
        this.tnumber = tnumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }
}
