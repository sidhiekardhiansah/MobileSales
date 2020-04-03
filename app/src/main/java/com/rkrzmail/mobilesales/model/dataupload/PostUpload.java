
package com.rkrzmail.mobilesales.model.dataupload;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostUpload implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Creator<PostUpload> CREATOR = new Creator<PostUpload>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PostUpload createFromParcel(Parcel in) {
            return new PostUpload(in);
        }

        public PostUpload[] newArray(int size) {
            return (new PostUpload[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1810692780659791165L;

    protected PostUpload(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PostUpload() {
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
