
package com.rkrzmail.mobilesales.model.reason;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Serializable, Parcelable
{

    @SerializedName("Reason_ID")
    @Expose
    private String reasonID;
    @SerializedName("Reason_Code")
    @Expose
    private String reasonCode;
    @SerializedName("Reason")
    @Expose
    private String reason;
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
    private final static long serialVersionUID = -2424490087663023960L;

    protected Datum(Parcel in) {
        this.reasonID = ((String) in.readValue((String.class.getClassLoader())));
        this.reasonCode = ((String) in.readValue((String.class.getClassLoader())));
        this.reason = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Datum() {
    }

    public String getReasonID() {
        return reasonID;
    }

    public void setReasonID(String reasonID) {
        this.reasonID = reasonID;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(reasonID);
        dest.writeValue(reasonCode);
        dest.writeValue(reason);
    }

    public int describeContents() {
        return  0;
    }

}
