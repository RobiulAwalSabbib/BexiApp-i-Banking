package com.bcl.bexiapp_i_banking.Transaction_Medium_Get_All_Data;

public class TransactionMediumDataModel {

    public String tm_id,tm_desc,tm_status;

    public TransactionMediumDataModel(String tm_id, String tm_desc, String tm_status) {
        this.tm_id = tm_id;
        this.tm_desc = tm_desc;
        this.tm_status = tm_status;
    }

    public String getTm_id() {
        return tm_id;
    }

    public void setTm_id(String tm_id) {
        this.tm_id = tm_id;
    }

    public String getTm_desc() {
        return tm_desc;
    }

    public void setTm_desc(String tm_desc) {
        this.tm_desc = tm_desc;
    }

    public String getTm_status() {
        return tm_status;
    }

    public void setTm_status(String tm_status) {
        this.tm_status = tm_status;
    }
}
