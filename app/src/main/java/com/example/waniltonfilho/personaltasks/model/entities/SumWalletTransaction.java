package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wanilton.filho on 09/05/2016.
 */
public class SumWalletTransaction implements Parcelable {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("price")
    private Float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.price);
    }

    public SumWalletTransaction() {
    }

    protected SumWalletTransaction(Parcel in) {
        this.id = in.readString();
        this.price = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Creator<SumWalletTransaction> CREATOR = new Creator<SumWalletTransaction>() {
        @Override
        public SumWalletTransaction createFromParcel(Parcel source) {
            return new SumWalletTransaction(source);
        }

        @Override
        public SumWalletTransaction[] newArray(int size) {
            return new SumWalletTransaction[size];
        }
    };
}
