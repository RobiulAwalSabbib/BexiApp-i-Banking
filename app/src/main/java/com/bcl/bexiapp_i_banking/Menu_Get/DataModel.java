package com.bcl.bexiapp_i_banking.Menu_Get;

public class DataModel {

    private String SL_NO, UNIT_TYPE, MENU_CODE, PARENT_CODE, DESCRIPTION,
            WEB_PARM_MOB_NO, WEB_PARM_TO_ACC_NO, WEB_PARM_TRAN_AMT, WEB_PARM_PIN_NO, PERM_ADD,
            PERM_MODIFY, PERM_DELETE, PERM_VIEW, CREATED_CODE, CREATED_DATE;


    public DataModel(String SL_NO, String UNIT_TYPE, String MENU_CODE, String PARENT_CODE, String DESCRIPTION, String WEB_PARM_MOB_NO, String WEB_PARM_TO_ACC_NO, String WEB_PARM_TRAN_AMT, String WEB_PARM_PIN_NO, String PERM_ADD, String PERM_MODIFY, String PERM_DELETE, String PERM_VIEW, String CREATED_CODE, String CREATED_DATE) {
        this.SL_NO = SL_NO;
        this.UNIT_TYPE = UNIT_TYPE;
        this.MENU_CODE = MENU_CODE;
        this.PARENT_CODE = PARENT_CODE;
        this.DESCRIPTION = DESCRIPTION;
        this.WEB_PARM_MOB_NO = WEB_PARM_MOB_NO;
        this.WEB_PARM_TO_ACC_NO = WEB_PARM_TO_ACC_NO;
        this.WEB_PARM_TRAN_AMT = WEB_PARM_TRAN_AMT;
        this.WEB_PARM_PIN_NO = WEB_PARM_PIN_NO;
        this.PERM_ADD = PERM_ADD;
        this.PERM_MODIFY = PERM_MODIFY;
        this.PERM_DELETE = PERM_DELETE;
        this.PERM_VIEW = PERM_VIEW;
        this.CREATED_CODE = CREATED_CODE;
        this.CREATED_DATE = CREATED_DATE;
    }

    public String getSL_NO() {
        return SL_NO;
    }

    public void setSL_NO(String SL_NO) {
        this.SL_NO = SL_NO;
    }

    public String getUNIT_TYPE() {
        return UNIT_TYPE;
    }

    public void setUNIT_TYPE(String UNIT_TYPE) {
        this.UNIT_TYPE = UNIT_TYPE;
    }

    public String getMENU_CODE() {
        return MENU_CODE;
    }

    public void setMENU_CODE(String MENU_CODE) {
        this.MENU_CODE = MENU_CODE;
    }

    public String getPARENT_CODE() {
        return PARENT_CODE;
    }

    public void setPARENT_CODE(String PARENT_CODE) {
        this.PARENT_CODE = PARENT_CODE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getWEB_PARM_MOB_NO() {
        return WEB_PARM_MOB_NO;
    }

    public void setWEB_PARM_MOB_NO(String WEB_PARM_MOB_NO) {
        this.WEB_PARM_MOB_NO = WEB_PARM_MOB_NO;
    }

    public String getWEB_PARM_TO_ACC_NO() {
        return WEB_PARM_TO_ACC_NO;
    }

    public void setWEB_PARM_TO_ACC_NO(String WEB_PARM_TO_ACC_NO) {
        this.WEB_PARM_TO_ACC_NO = WEB_PARM_TO_ACC_NO;
    }

    public String getWEB_PARM_TRAN_AMT() {
        return WEB_PARM_TRAN_AMT;
    }

    public void setWEB_PARM_TRAN_AMT(String WEB_PARM_TRAN_AMT) {
        this.WEB_PARM_TRAN_AMT = WEB_PARM_TRAN_AMT;
    }

    public String getWEB_PARM_PIN_NO() {
        return WEB_PARM_PIN_NO;
    }

    public void setWEB_PARM_PIN_NO(String WEB_PARM_PIN_NO) {
        this.WEB_PARM_PIN_NO = WEB_PARM_PIN_NO;
    }

    public String getPERM_ADD() {
        return PERM_ADD;
    }

    public void setPERM_ADD(String PERM_ADD) {
        this.PERM_ADD = PERM_ADD;
    }

    public String getPERM_MODIFY() {
        return PERM_MODIFY;
    }

    public void setPERM_MODIFY(String PERM_MODIFY) {
        this.PERM_MODIFY = PERM_MODIFY;
    }

    public String getPERM_DELETE() {
        return PERM_DELETE;
    }

    public void setPERM_DELETE(String PERM_DELETE) {
        this.PERM_DELETE = PERM_DELETE;
    }

    public String getPERM_VIEW() {
        return PERM_VIEW;
    }

    public void setPERM_VIEW(String PERM_VIEW) {
        this.PERM_VIEW = PERM_VIEW;
    }

    public String getCREATED_CODE() {
        return CREATED_CODE;
    }

    public void setCREATED_CODE(String CREATED_CODE) {
        this.CREATED_CODE = CREATED_CODE;
    }

    public String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(String CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }
}
