package com.example.mobileapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class pharmacyRVModal implements Parcelable {

    private String pharmacyIName;
    private String pharmacyIDescription;
    private String pharmacyIPrice;
    private String pharmacyI_Img;
    private String pharmacyI_ID;

    public pharmacyRVModal(){

    }

    public pharmacyRVModal(String pharmacyIName, String pharmacyIDescription, String pharmacyIPrice, String pharmacyI_Img, String pharmacyI_ID) {
        this.pharmacyIName = pharmacyIName;
        this.pharmacyIDescription = pharmacyIDescription;
        this.pharmacyIPrice = pharmacyIPrice;
        this.pharmacyI_Img = pharmacyI_Img;
        this.pharmacyI_ID = pharmacyI_ID;
    }

    protected pharmacyRVModal(Parcel in) {
        pharmacyIName = in.readString();
        pharmacyIDescription = in.readString();
        pharmacyIPrice = in.readString();
        pharmacyI_Img = in.readString();
        pharmacyI_ID = in.readString();
    }

    public static final Creator<pharmacyRVModal> CREATOR = new Creator<pharmacyRVModal>() {
        @Override
        public pharmacyRVModal createFromParcel(Parcel in) {
            return new pharmacyRVModal(in);
        }

        @Override
        public pharmacyRVModal[] newArray(int size) {
            return new pharmacyRVModal[size];
        }
    };

    public String getPharmacyIName() {
        return pharmacyIName;
    }

    public void setPharmacyIName(String pharmacyIName) {
        this.pharmacyIName = pharmacyIName;
    }

    public String getPharmacyIDescription() {
        return pharmacyIDescription;
    }

    public void setPharmacyIDescription(String pharmacyIDescription) {
        this.pharmacyIDescription = pharmacyIDescription;
    }

    public String getPharmacyIPrice() {
        return pharmacyIPrice;
    }

    public void setPharmacyIPrice(String pharmacyIPrice) {
        this.pharmacyIPrice = pharmacyIPrice;
    }

    public String getPharmacyI_Img() {
        return pharmacyI_Img;
    }

    public void setPharmacyI_Img(String pharmacyI_Img) {
        this.pharmacyI_Img = pharmacyI_Img;
    }

    public String getPharmacyI_ID() {
        return pharmacyI_ID;
    }

    public void setPharmacyI_ID(String pharmacyI_ID) {
        this.pharmacyI_ID = pharmacyI_ID;
    }

    public static Creator<pharmacyRVModal> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pharmacyIName);
        parcel.writeString(pharmacyIDescription);
        parcel.writeString(pharmacyIPrice);
        parcel.writeString(pharmacyI_Img);
        parcel.writeString(pharmacyI_ID);
    }
}
