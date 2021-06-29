package com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data;

public class TransactionTypeDataModel {

    public String tt_id,tt_desc,tt_status;

    public TransactionTypeDataModel(String tt_id, String tt_desc, String tt_status) {
        this.tt_id = tt_id;
        this.tt_desc = tt_desc;
        this.tt_status = tt_status;
    }


    public String getTt_id() {
        return tt_id;
    }

    public void setTt_id(String tt_id) {
        this.tt_id = tt_id;
    }

    public String getTt_desc() {
        return tt_desc;
    }

    public void setTt_desc(String tt_desc) {
        this.tt_desc = tt_desc;
    }

    public String getTt_status() {
        return tt_status;
    }

    public void setTt_status(String tt_status) {
        this.tt_status = tt_status;
    }
}
