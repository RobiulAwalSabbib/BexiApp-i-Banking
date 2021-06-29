package com.bcl.bexiapp_i_banking.Menu_Get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiveModel {

    @SerializedName("UNIT_TYPE")
    @Expose
    private String unitType;
    @SerializedName("WEB_PARM_TRAN_AMT")
    @Expose
    private String webParmTranAmt;
    @SerializedName("CREATED_DATE")
    @Expose
    private String createdDate;
    @SerializedName("PERM_DELETE")
    @Expose
    private String permDelete;
    @SerializedName("PERM_VIEW")
    @Expose
    private String permView;
    @SerializedName("PERM_ADD")
    @Expose
    private String permAdd;
    @SerializedName("SL_NO")
    @Expose
    private String slNo;
    @SerializedName("PARENT_CODE")
    @Expose
    private String parentCode;
    @SerializedName("DESCRIPTION")
    @Expose
    private String description;
    @SerializedName("WEB_PARM_MOB_NO")
    @Expose
    private String webParmMobNo;
    @SerializedName("CREATED_CODE")
    @Expose
    private String createdCode;
    @SerializedName("WEB_PARM_TO_ACC_NO")
    @Expose
    private String webParmToAccNo;
    @SerializedName("WEB_PARM_PIN_NO")
    @Expose
    private String webParmPinNo;
    @SerializedName("PERM_MODIFY")
    @Expose
    private String permModify;
    @SerializedName("MENU_CODE")
    @Expose
    private String menuCode;

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getWebParmTranAmt() {
        return webParmTranAmt;
    }

    public void setWebParmTranAmt(String webParmTranAmt) {
        this.webParmTranAmt = webParmTranAmt;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPermDelete() {
        return permDelete;
    }

    public void setPermDelete(String permDelete) {
        this.permDelete = permDelete;
    }

    public String getPermView() {
        return permView;
    }

    public void setPermView(String permView) {
        this.permView = permView;
    }

    public String getPermAdd() {
        return permAdd;
    }

    public void setPermAdd(String permAdd) {
        this.permAdd = permAdd;
    }

    public String getSlNo() {
        return slNo;
    }

    public void setSlNo(String slNo) {
        this.slNo = slNo;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebParmMobNo() {
        return webParmMobNo;
    }

    public void setWebParmMobNo(String webParmMobNo) {
        this.webParmMobNo = webParmMobNo;
    }

    public String getCreatedCode() {
        return createdCode;
    }

    public void setCreatedCode(String createdCode) {
        this.createdCode = createdCode;
    }

    public String getWebParmToAccNo() {
        return webParmToAccNo;
    }

    public void setWebParmToAccNo(String webParmToAccNo) {
        this.webParmToAccNo = webParmToAccNo;
    }

    public String getWebParmPinNo() {
        return webParmPinNo;
    }

    public void setWebParmPinNo(String webParmPinNo) {
        this.webParmPinNo = webParmPinNo;
    }

    public String getPermModify() {
        return permModify;
    }

    public void setPermModify(String permModify) {
        this.permModify = permModify;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }



}
