package com.example.mobileapplication.models;

public class PaymentModel {

    private String id;
    private String CardHolderName;
    private String CardNumber;
    private String ExpireDate;
    private String Cvv;

    public PaymentModel() {};

    public PaymentModel(String id, String CardHolderName, String CardNumber, String ExpireDate, String Cvv){

        this.id = id;
        this.CardHolderName = CardHolderName;
        this.CardNumber = CardNumber;
        this.ExpireDate = ExpireDate;
        this.Cvv = Cvv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return CardHolderName;
    }

    public void setCardHolderName(String CardHolderName) {
        this.CardHolderName = CardHolderName;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String CardNumber) {
        this.CardNumber = CardNumber;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String ExpireDate) {
        this.ExpireDate = ExpireDate;
    }

    public String getCvv() {
        return Cvv;
    }

    public void setCvv(String Cvv) {
        this.Cvv = Cvv;
    }
}
