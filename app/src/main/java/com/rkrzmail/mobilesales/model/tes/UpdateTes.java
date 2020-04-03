
package com.rkrzmail.mobilesales.model.tes;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTes implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<UpdateTes> CREATOR = new Creator<UpdateTes>() {


        @SuppressWarnings({
            "unchecked"
        })
        public UpdateTes createFromParcel(Parcel in) {
            return new UpdateTes(in);
        }

        public UpdateTes[] newArray(int size) {
            return (new UpdateTes[size]);
        }

    }
    ;
    private final static long serialVersionUID = -2230165454647681550L;

    protected UpdateTes(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UpdateTes() {
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
