package com.example.waniltonfilho.personaltasks.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wanilton.filho on 19/01/2016.
 */
public class Login implements Parcelable {
    private Long id;
    private String login;
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Login login1 = (Login) o;

        if (id != null ? !id.equals(login1.id) : login1.id != null) return false;
        if (login != null ? !login.equals(login1.login) : login1.login != null) return false;
        return !(password != null ? !password.equals(login1.password) : login1.password != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.login);
        dest.writeString(this.password);
    }

    public Login() {
    }

    protected Login(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.login = in.readString();
        this.password = in.readString();
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
