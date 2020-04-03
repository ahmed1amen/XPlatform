package com.tahhan.xprojectbeta.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String id;
    private String name;
    private String emailAddress;
    private boolean isClient;
    private boolean isTransporter;
    private boolean isCoordinator;
    private Client client;
    private Transporter transporter;
    private Coordinator coordinator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public boolean isTransporter() {
        return isTransporter;
    }

    public void setTransporter(boolean transporter) {
        isTransporter = transporter;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }

    public void setCoordinator(boolean coordinator) {
        isCoordinator = coordinator;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public User() {
    }

    public User(String id, String name, String emailAddress, boolean isClient, boolean isTransporter, boolean isCoordinator, Client client, Transporter transporter, Coordinator coordinator) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.isClient = isClient;
        this.isTransporter = isTransporter;
        this.isCoordinator = isCoordinator;
        this.client = client;
        this.transporter = transporter;
        this.coordinator = coordinator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.emailAddress);
        dest.writeByte(this.isClient ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isTransporter ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCoordinator ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.client, flags);
        dest.writeParcelable(this.transporter, flags);
        dest.writeParcelable(this.coordinator, flags);
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.emailAddress = in.readString();
        this.isClient = in.readByte() != 0;
        this.isTransporter = in.readByte() != 0;
        this.isCoordinator = in.readByte() != 0;
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.transporter = in.readParcelable(Transporter.class.getClassLoader());
        this.coordinator = in.readParcelable(Coordinator.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
