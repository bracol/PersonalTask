package com.example.waniltonfilho.personaltasks.model.entities;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wanilton.filho on 29/01/2016.
 */
public class WalletTransaction implements Parcelable, Comparable<WalletTransaction> {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("desc")
    private String name;

    @JsonProperty("date")
    private String date;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("wallet")
    private String wallet_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }



    @Override
    public int compareTo(WalletTransaction another) {
        int thisDia = Integer.parseInt(this.getDate().substring(8, 10));
        int anotherDia = Integer.parseInt(another.getDate().substring(8, 10));
        if (!(another instanceof WalletTransaction))
            throw new ClassCastException("Classe não esperada.");
        if (thisDia < anotherDia) {
            return -1;
        }
        if (thisDia > anotherDia) {
            return 1;
        }
        return 0;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.date);
        dest.writeValue(this.price);
        dest.writeParcelable(this.category, 0);
        dest.writeString(this.wallet_id);
    }

    public WalletTransaction() {
    }

    protected WalletTransaction(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.date = in.readString();
        this.price = (Float) in.readValue(Float.class.getClassLoader());
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.wallet_id = in.readString();
    }

    public static final Creator<WalletTransaction> CREATOR = new Creator<WalletTransaction>() {
        public WalletTransaction createFromParcel(Parcel source) {
            return new WalletTransaction(source);
        }

        public WalletTransaction[] newArray(int size) {
            return new WalletTransaction[size];
        }
    };
}