package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wanilton.filho on 26/04/2016.
 */
public class GroupCategoryTransaction implements Parcelable {

    @JsonProperty("_id")
    private String _id;

    @JsonProperty("total")
    private Float total;

    public GroupCategoryTransaction() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeValue(this.total);
    }

    protected GroupCategoryTransaction(Parcel in) {
        this._id = in.readString();
        this.total = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Parcelable.Creator<GroupCategoryTransaction> CREATOR = new Parcelable.Creator<GroupCategoryTransaction>() {
        public GroupCategoryTransaction createFromParcel(Parcel source) {
            return new GroupCategoryTransaction(source);
        }

        public GroupCategoryTransaction[] newArray(int size) {
            return new GroupCategoryTransaction[size];
        }
    };
}
