package com.example.mobileapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class groceryRVModal implements Parcelable {

    private String groceryName;
    private String groceryDescription;
    private String groceryPrice;
    private String groceryImg;
    private String groceryID;

    public groceryRVModal(){

    }

    public groceryRVModal(String groceryName, String groceryDescription, String groceryPrice, String groceryImg, String groceryID) {
        this.groceryName = groceryName;
        this.groceryDescription = groceryDescription;
        this.groceryPrice = groceryPrice;
        this.groceryImg = groceryImg;
        this.groceryID = groceryID;
    }

    protected groceryRVModal(Parcel in) {
        groceryName = in.readString();
        groceryDescription = in.readString();
        groceryPrice = in.readString();
        groceryImg = in.readString();
        groceryID = in.readString();
    }

    public static final Creator<groceryRVModal> CREATOR = new Creator<groceryRVModal>() {
        @Override
        public groceryRVModal createFromParcel(Parcel in) {
            return new groceryRVModal(in);
        }

        @Override
        public groceryRVModal[] newArray(int size) {
            return new groceryRVModal[size];
        }
    };

    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    public String getGroceryDescription() {
        return groceryDescription;
    }

    public void setGroceryDescription(String groceryDescription) {
        this.groceryDescription = groceryDescription;
    }

    public String getGroceryPrice() {
        return groceryPrice;
    }

    public void setGroceryPrice(String groceryPrice) {
        this.groceryPrice = groceryPrice;
    }

    public String getGroceryImg() {
        return groceryImg;
    }

    public void setGroceryImg(String groceryImg) {
        this.groceryImg = groceryImg;
    }

    public String getGroceryID() {
        return groceryID;
    }

    public void setGroceryID(String groceryID) {
        this.groceryID = groceryID;
    }

    public static Creator<groceryRVModal> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(groceryName);
        parcel.writeString(groceryDescription);
        parcel.writeString(groceryPrice);
        parcel.writeString(groceryImg);
        parcel.writeString(groceryID);
    }
}
