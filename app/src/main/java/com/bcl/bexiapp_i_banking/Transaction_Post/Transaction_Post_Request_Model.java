package com.bcl.bexiapp_i_banking.Transaction_Post;

public class Transaction_Post_Request_Model {

    private String sender_acc, receiver_acc, transaction_amount,transaction_medium,transaction_type;

    public String getSender_acc() {
        return sender_acc;
    }

    public void setSender_acc(String sender_acc) {
        this.sender_acc = sender_acc;
    }

    public String getReceiver_acc() {
        return receiver_acc;
    }

    public void setReceiver_acc(String receiver_acc) {
        this.receiver_acc = receiver_acc;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_medium() {
        return transaction_medium;
    }

    public void setTransaction_medium(String transaction_medium) {
        this.transaction_medium = transaction_medium;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }



}
