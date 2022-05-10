package com.example.mobileapplication.models;

public class CustomerModel {

    private String id;
    private String Name;
    private String EmailAddress;
    private String PhoneNumber;
    private String Password;
    private String Cpassword;

    public CustomerModel() {};

    public CustomerModel(String id, String Name,String EmailAddress,String PhoneNumber, String Password,String Cpassword) {

        this.id = id;
        this.Name = Name;
        this.EmailAddress = EmailAddress;
        this.PhoneNumber = PhoneNumber;
        this.Password = Password;
        this.Cpassword = Cpassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String EmailAddress) {
        this.EmailAddress = EmailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getCpassword() {
        return Cpassword;
    }

    public void setCpassword(String Cpassword) {
        this.Cpassword = Cpassword;
    }
}
