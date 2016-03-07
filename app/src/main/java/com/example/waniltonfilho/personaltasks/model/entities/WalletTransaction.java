package com.example.waniltonfilho.personaltasks.model.entities;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wanilton.filho on 29/01/2016.
 */
public class WalletTransaction implements Parcelable {

    private Long id;
    private String name;
    private String date;
    private Double price;
    private int action;
    private Long login_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        try {
            if (date.contains("/")) {
                DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date inputDate = inputFormat.parse(date);
                String outputDateStr = outputFormat.format(inputDate);
                this.date = outputDateStr;
            } else {
                this.date = date;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Long getLogin_id() {
        return login_id;
    }

    public void setLogin_id(Long login_id) {
        this.login_id = login_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.date);
        dest.writeValue(this.price);
        dest.writeInt(this.action);
        dest.writeValue(this.login_id);
    }

    public WalletTransaction() {
    }

    protected WalletTransaction(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.date = in.readString();
        this.price = (Double) in.readValue(Double.class.getClassLoader());
        this.action = in.readInt();
        this.login_id = (Long) in.readValue(Long.class.getClassLoader());
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
