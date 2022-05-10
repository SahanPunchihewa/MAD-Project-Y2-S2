package com.example.mobileapplication;

public class BakeryRVModal {
    private String bakeryIName;
    private String bakeryIDescription;
    private String bakeryIPrice;
    private String bakeryI_Img;
    private String bakeryI_ID;

    public BakeryRVModal() {


    }

    public BakeryRVModal(String bakeryIName, String bakeryIDescription, String bakeryIPrice, String bakeryI_Img, String bakeryI_ID) {
        this.bakeryIName = bakeryIName;
        this.bakeryIDescription = bakeryIDescription;
        this.bakeryIPrice = bakeryIPrice;
        this.bakeryI_Img = bakeryI_Img;
        this.bakeryI_ID = bakeryI_ID;
    }

    public String getBakeryIName() {
        return bakeryIName;
    }

    public void setBakeryIName(String bakeryIName) {
        this.bakeryIName = bakeryIName;
    }

    public String getBakeryIDescription() {
        return bakeryIDescription;
    }

    public void setBakeryIDescription(String bakeryIDescription) {
        this.bakeryIDescription = bakeryIDescription;
    }

    public String getBakeryIPrice() {
        return bakeryIPrice;
    }

    public void setBakeryIPrice(String bakeryIPrice) {
        this.bakeryIPrice = bakeryIPrice;
    }

    public String getBakeryI_Img() {
        return bakeryI_Img;
    }

    public void setBakeryI_Img(String bakeryI_Img) {
        this.bakeryI_Img = bakeryI_Img;
    }

    public String getBakeryI_ID() {
        return bakeryI_ID;
    }

    public void setBakeryI_ID(String bakeryI_ID) {
        this.bakeryI_ID = bakeryI_ID;
    }

    public enum ViewHolder {

    }


    }

