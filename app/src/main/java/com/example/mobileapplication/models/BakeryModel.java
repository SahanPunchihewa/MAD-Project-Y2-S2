package com.example.mobileapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BakeryModel implements Parcelable {

    private String bakeryName;
    private String bakeryDescription;
    private String bakeryPrice;
    private String bakeryImg;
    private String bakeryID;

    public BakeryModel(){

    }

    public BakeryModel(String bakeryName, String bakeryDescription, String bakeryPrice, String bakeryImg, String bakeryID) {
        this.bakeryName = bakeryName;
        this.bakeryDescription = bakeryDescription;
        this.bakeryPrice = bakeryPrice;
        this.bakeryImg = bakeryImg;
        this.bakeryID = bakeryID;
    }

    protected BakeryModel(Parcel in) {
        bakeryName = in.readString();
        bakeryDescription = in.readString();
        bakeryPrice = in.readString();
        bakeryImg = in.readString();
        bakeryID = in.readString();
    }

    public static final Creator<BakeryModel> CREATOR = new Creator<BakeryModel>() {
        @Override
        public BakeryModel createFromParcel(Parcel in) {
            return new BakeryModel(in);
        }

        @Override
        public BakeryModel[] newArray(int size) {
            return new BakeryModel[size];
        }
    };

    public String getBakeryName() {
        return bakeryName;
    }

    public void setBakeryName(String bakeryName) {
        this.bakeryName = bakeryName;
    }

    public String getBakeryDescription() {
        return bakeryDescription;
    }

    public void setBakeryDescription(String bakeryDescription) {
        this.bakeryDescription = bakeryDescription;
    }

    public String getBakeryPrice() {
        return bakeryPrice;
    }

    public void setBakeryPrice(String bakeryPrice) {
        this.bakeryPrice = bakeryPrice;
    }

    public String getBakeryImg() {
        return bakeryImg;
    }

    public void setBakeryImg(String bakeryImg) {
        this.bakeryImg = bakeryImg;
    }

    public String getBakeryID() {
        return bakeryID;
    }

    public void setBakeryID(String bakeryID) {
        this.bakeryID = bakeryID;
    }

    public static Creator<BakeryModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bakeryName);
        parcel.writeString(bakeryDescription);
        parcel.writeString(bakeryPrice);
        parcel.writeString(bakeryImg);
        parcel.writeString(bakeryID);
    }
}
