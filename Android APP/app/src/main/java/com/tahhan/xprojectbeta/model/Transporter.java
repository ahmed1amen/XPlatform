package com.tahhan.xprojectbeta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transporter implements Parcelable {
    int id ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    public Transporter() {
    }

    protected Transporter(Parcel in) {
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<Transporter> CREATOR = new Parcelable.Creator<Transporter>() {
        @Override
        public Transporter createFromParcel(Parcel source) {
            return new Transporter(source);
        }

        @Override
        public Transporter[] newArray(int size) {
            return new Transporter[size];
        }
    };
}
