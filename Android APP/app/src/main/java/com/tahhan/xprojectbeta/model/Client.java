package com.tahhan.xprojectbeta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Client implements Parcelable {
    int id ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    public Client() {
    }

    protected Client(Parcel in) {
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
