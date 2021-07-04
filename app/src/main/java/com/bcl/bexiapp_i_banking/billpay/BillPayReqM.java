package com.bcl.bexiapp_i_banking.billpay;

public class BillPayReqM {

    private String acc_no, mobile_no, billpay__name, billpay_type,bill_no, meter_no, amount, pin;

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getBillpay__name() {
        return billpay__name;
    }

    public void setBillpay__name(String billpay__name) {
        this.billpay__name = billpay__name;
    }

    public String getBillpay_type() {
        return billpay_type;
    }

    public void setBillpay_type(String billpay_type) {
        this.billpay_type = billpay_type;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getMeter_no() {
        return meter_no;
    }

    public void setMeter_no(String meter_no) {
        this.meter_no = meter_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
