package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

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
        this.date = date;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WalletTransaction that = (WalletTransaction) o;

        if (action != that.action) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return !(login_id != null ? !login_id.equals(that.login_id) : that.login_id != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + action;
        result = 31 * result + (login_id != null ? login_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WalletTransaction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", action=" + action +
                ", login_id=" + login_id +
                '}';
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

    public static final Parcelable.Creator<WalletTransaction> CREATOR = new Parcelable.Creator<WalletTransaction>() {
        public WalletTransaction createFromParcel(Parcel source) {
            return new WalletTransaction(source);
        }

        public WalletTransaction[] newArray(int size) {
            return new WalletTransaction[size];
        }
    };
}
