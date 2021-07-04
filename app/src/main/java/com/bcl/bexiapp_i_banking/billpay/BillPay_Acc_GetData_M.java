package com.bcl.bexiapp_i_banking.billpay;

public class BillPay_Acc_GetData_M {

    private String ACNUMBER, CUSTID, BID, OPENING_BALANCE, AOD, ATYPE, ASTATUS;

    public BillPay_Acc_GetData_M(String ACNUMBER, String CUSTID, String BID, String OPENING_BALANCE, String AOD, String ATYPE, String ASTATUS) {
        this.ACNUMBER = ACNUMBER;
        this.CUSTID = CUSTID;
        this.BID = BID;
        this.OPENING_BALANCE = OPENING_BALANCE;
        this.AOD = AOD;
        this.ATYPE = ATYPE;
        this.ASTATUS = ASTATUS;
    }

    public String getACNUMBER() {
        return ACNUMBER;
    }

    public void setACNUMBER(String ACNUMBER) {
        this.ACNUMBER = ACNUMBER;
    }

    public String getCUSTID() {
        return CUSTID;
    }

    public void setCUSTID(String CUSTID) {
        this.CUSTID = CUSTID;
    }

    public String getBID() {
        return BID;
    }

    public void setBID(String BID) {
        this.BID = BID;
    }

    public String getOPENING_BALANCE() {
        return OPENING_BALANCE;
    }

    public void setOPENING_BALANCE(String OPENING_BALANCE) {
        this.OPENING_BALANCE = OPENING_BALANCE;
    }

    public String getAOD() {
        return AOD;
    }

    public void setAOD(String AOD) {
        this.AOD = AOD;
    }

    public String getATYPE() {
        return ATYPE;
    }

    public void setATYPE(String ATYPE) {
        this.ATYPE = ATYPE;
    }

    public String getASTATUS() {
        return ASTATUS;
    }

    public void setASTATUS(String ASTATUS) {
        this.ASTATUS = ASTATUS;
    }
}
