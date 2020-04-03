package com.tahhan.xprojectbeta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Coordinator implements Parcelable {
    String id ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    public Coordinator() {
    }

    protected Coordinator(Parcel in) {
        this.id = in.readString();
    }

    public static final Parcelable.Creator<Coordinator> CREATOR = new Parcelable.Creator<Coordinator>() {
        @Override
        public Coordinator createFromParcel(Parcel source) {
            return new Coordinator(source);
        }

        @Override
        public Coordinator[] newArray(int size) {
            return new Coordinator[size];
        }
    };
}
