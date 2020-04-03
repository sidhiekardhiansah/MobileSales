
package com.rkrzmail.mobilesales.model.tes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class modeltes implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<modeltes> CREATOR = new Creator<modeltes>() {


        @SuppressWarnings({
            "unchecked"
        })
        public modeltes createFromParcel(Parcel in) {
            return new modeltes(in);
        }

        public modeltes[] newArray(int size) {
            return (new modeltes[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5880193300032161274L;

    protected modeltes(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public modeltes() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
