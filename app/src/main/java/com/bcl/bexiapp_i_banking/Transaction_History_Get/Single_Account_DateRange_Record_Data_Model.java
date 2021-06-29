package com.bcl.bexiapp_i_banking.Transaction_History_Get;

public class Single_Account_DateRange_Record_Data_Model {

    private String SL_NO,TRAN_NO,DAT,FROM_ACC,TRAN_MEDIUM,TRAN_TYPE,TRAN_AMOUNT,TO_ACC,OLD_BALANCE,NEW_BALANCE;


    public Single_Account_DateRange_Record_Data_Model() {
    }

    public Single_Account_DateRange_Record_Data_Model(String SL_NO, String TRAN_NO, String DAT, String FROM_ACC, String TRAN_MEDIUM, String TRAN_TYPE, String TRAN_AMOUNT, String TO_ACC, String OLD_BALANCE, String NEW_BALANCE) {
        this.SL_NO = SL_NO;
        this.TRAN_NO = TRAN_NO;
        this.DAT = DAT;
        this.FROM_ACC = FROM_ACC;
        this.TRAN_MEDIUM = TRAN_MEDIUM;
        this.TRAN_TYPE = TRAN_TYPE;
        this.TRAN_AMOUNT = TRAN_AMOUNT;
        this.TO_ACC = TO_ACC;
        this.OLD_BALANCE = OLD_BALANCE;
        this.NEW_BALANCE = NEW_BALANCE;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getTRAN_NO() {
        return TRAN_NO;
    }

    public void setTRAN_NO(String TRAN_NO) {
        this.TRAN_NO = TRAN_NO;
    }

    public String getDAT() {
        return DAT;
    }

    public void setDAT(String DAT) {
        this.DAT = DAT;
    }

    public String getFROM_ACC() {
        return FROM_ACC;
    }

    public void setFROM_ACC(String FROM_ACC) {
        this.FROM_ACC = FROM_ACC;
    }

    public String getTRAN_MEDIUM() {
        return TRAN_MEDIUM;
    }

    public void setTRAN_MEDIUM(String TRAN_MEDIUM) {
        this.TRAN_MEDIUM = TRAN_MEDIUM;
    }

    public String getTRAN_TYPE() {
        return TRAN_TYPE;
    }

    public void setTRAN_TYPE(String TRAN_TYPE) {
        this.TRAN_TYPE = TRAN_TYPE;
    }

    public String getTRAN_AMOUNT() {
        return TRAN_AMOUNT;
    }

    public void setTRAN_AMOUNT(String TRAN_AMOUNT) {
        this.TRAN_AMOUNT = TRAN_AMOUNT;
    }

    public String getTO_ACC() {
        return TO_ACC;
    }

    public void setTO_ACC(String TO_ACC) {
        this.TO_ACC = TO_ACC;
    }

    public String getOLD_BALANCE() {
        return OLD_BALANCE;
    }

    public void setOLD_BALANCE(String OLD_BALANCE) {
        this.OLD_BALANCE = OLD_BALANCE;
    }

    public String getNEW_BALANCE() {
        return NEW_BALANCE;
    }

    public void setNEW_BALANCE(String NEW_BALANCE) {
        this.NEW_BALANCE = NEW_BALANCE;
    }
}
