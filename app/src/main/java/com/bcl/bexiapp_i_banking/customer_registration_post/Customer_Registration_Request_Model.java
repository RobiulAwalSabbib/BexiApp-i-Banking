package com.bcl.bexiapp_i_banking.customer_registration_post;

public class Customer_Registration_Request_Model {

    private String applicant_name;
    private String mobile;
    private String email;
    private String dob;
    private String passwords;
    private String confirm_passwords;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }



    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getConfirm_passwords() {
        return confirm_passwords;
    }

    public void setConfirm_passwords(String confirm_passwords) {
        this.confirm_passwords = confirm_passwords;
    }


}
