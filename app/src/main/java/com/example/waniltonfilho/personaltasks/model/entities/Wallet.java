package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wanilton on 28/02/2016.
 */
public class Wallet implements Parcelable {

    private Long _id;
    private Float value;


    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeValue(this.value);
    }

    public Wallet() {
    }

    protected Wallet(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.value = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
        public Wallet createFromParcel(Parcel source) {
            return new Wallet(source);
        }

        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };
}