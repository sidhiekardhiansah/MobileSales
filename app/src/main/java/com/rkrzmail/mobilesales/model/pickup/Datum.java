
package com.rkrzmail.mobilesales.model.pickup;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable, Parcelable
{

    @SerializedName("Status_ID")
    @Expose
    private String statusID;
    @SerializedName("kode_pick_up")
    @Expose
    private String kodePickUp;
    @SerializedName("Status")
    @Expose
    private String status;
    public final static Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8810607189016352761L;

    protected Datum(Parcel in) {
        this.statusID = ((String) in.readValue((String.class.getClassLoader())));
        this.kodePickUp = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getKodePickUp() {
        return kodePickUp;
    }

    public void setKodePickUp(String kodePickUp) {
        this.kodePickUp = kodePickUp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(statusID);
        dest.writeValue(kodePickUp);
        dest.writeValue(status);
    }

    public int describeContents() {
        return  0;
    }

}
