package com.bcl.bexiapp_i_banking.Benificier_Single_Account_Get;

public class Single_Account_Get_Data_Model {

    private String SLNO,BENI_ACC,REF_ACC,ACC_TYPE,ACC_TITLE;

    public Single_Account_Get_Data_Model(String SLNO, String BENI_ACC, String REF_ACC, String ACC_TYPE, String ACC_TITLE) {
        this.SLNO = SLNO;
        this.BENI_ACC = BENI_ACC;
        this.REF_ACC = REF_ACC;
        this.ACC_TYPE = ACC_TYPE;
        this.ACC_TITLE = ACC_TITLE;
    }

    public String getSLNO() {
        return SLNO;
    }

    public void setSLNO(String SLNO) {
        this.SLNO = SLNO;
    }

    public String getBENI_ACC() {
        return BENI_ACC;
    }

    public void setBENI_ACC(String BENI_ACC) {
        this.BENI_ACC = BENI_ACC;
    }

    public String getREF_ACC() {
        return REF_ACC;
    }

    public void setREF_ACC(String REF_ACC) {
        this.REF_ACC = REF_ACC;
    }

    public String getACC_TYPE() {
        return ACC_TYPE;
    }

    public void setACC_TYPE(String ACC_TYPE) {
        this.ACC_TYPE = ACC_TYPE;
    }

    public String getACC_TITLE() {
        return ACC_TITLE;
    }

    public void setACC_TITLE(String ACC_TITLE) {
        this.ACC_TITLE = ACC_TITLE;
    }

}
