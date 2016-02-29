package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wanilton on 28/02/2016.
 */
public class Wallet implements Parcelable {

    private Long _id;
    private Double value;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wallet wallet = (Wallet) o;

        if (_id != null ? !_id.equals(wallet._id) : wallet._id != null) return false;
        return !(value != null ? !value.equals(wallet.value) : wallet.value != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
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
        this.value = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Wallet> CREATOR = new Parcelable.Creator<Wallet>() {
        public Wallet createFromParcel(Parcel source) {
            return new Wallet(source);
        }

        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };
}
