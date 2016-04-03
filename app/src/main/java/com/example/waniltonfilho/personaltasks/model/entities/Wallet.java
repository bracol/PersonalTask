package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Wanilton on 28/02/2016.
 */
public class Wallet implements Parcelable {

    @JsonProperty("_id")
    private String _id;

    @JsonProperty("value")
    private Float value;

    @JsonIgnore
    private Login user;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Login getUser() {
        return user;
    }

    public void setUser(Login user) {
        this.user = user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeValue(this.value);
        dest.writeParcelable(this.user, flags);
    }

    public Wallet() {
    }

    protected Wallet(Parcel in) {
        this._id = in.readString();
        this.value = (Float) in.readValue(Float.class.getClassLoader());
        this.user = in.readParcelable(Login.class.getClassLoader());
    }

    public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
        @Override
        public Wallet createFromParcel(Parcel source) {
            return new Wallet(source);
        }

        @Override
        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };
}