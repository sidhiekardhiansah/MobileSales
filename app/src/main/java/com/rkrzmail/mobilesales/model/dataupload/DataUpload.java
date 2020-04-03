
package com.rkrzmail.mobilesales.model.dataupload;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUpload implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    public final static Creator<DataUpload> CREATOR = new Creator<DataUpload>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DataUpload createFromParcel(Parcel in) {
            return new DataUpload(in);
        }

        public DataUpload[] newArray(int size) {
            return (new DataUpload[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8206874873262330370L;

    protected DataUpload(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (com.rkrzmail.mobilesales.model.dataupload.Datum.class.getClassLoader()));
    }

    public DataUpload() {
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
