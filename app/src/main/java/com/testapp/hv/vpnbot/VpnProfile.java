package com.testapp.hv.vpnbot;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by hv on 7/8/16.
 */
public abstract class VpnProfile implements Parcelable, Serializable {
    private static final long serialVersionUID = 1L;
    private String mName; // unique display name
    private String mId; // unique identifier
    private String mServerName; // VPN server name
    private String mDomainSuffices; // space separated list
    private String mRouteList; // space separated list
    private String mSavedUsername;
    private boolean mIsCustomized;
    private transient VpnState mState = VpnState.IDLE;
    /** Sets a user-friendly name for this profile. */
    public void setName(String name) {
        mName = name;
    }
    public String getName() {
        return mName;
    }
    /**
     * Sets an ID for this profile.  The caller should make sure the
     * uniqueness of the ID.
     */
    public void setId(String id) {
        mId = id;
    }
    public String getId() {
        return mId;
    }
    /**
     * Sets the name of the VPN server. Used for DNS lookup.
     */
    public void setServerName(String name) {
        mServerName = name;
    }
    public String getServerName() {
        return mServerName;
    }
    /**
     * Sets the domain suffices for DNS resolution.
     *
     * @param entries a comma-separated list of domain suffices
     */
    public void setDomainSuffices(String entries) {
        mDomainSuffices = entries;
    }
    public String getDomainSuffices() {
        return mDomainSuffices;
    }
    /**
     * Sets the routing info for this VPN connection.
     *
     * @param entries a comma-separated list of routes; each entry is in the
     *      format of "(network address)/(network mask)"
     */
    public void setRouteList(String entries) {
        mRouteList = entries;
    }
    public String getRouteList() {
        return mRouteList;
    }
    public void setSavedUsername(String name) {
        mSavedUsername = name;
    }
    public String getSavedUsername() {
        return mSavedUsername;
    }
    public void setState(VpnState state) {
        mState = state;
    }
    public VpnState getState() {
        return ((mState == null) ? VpnState.IDLE : mState);
    }
    public boolean isIdle() {
        return (mState == VpnState.IDLE);
    }
    /**
     * Returns whether this profile is custom made (as opposed to being
     * created by provided user interface).
     */
    public boolean isCustomized() {
        return mIsCustomized;
    }
    /**
     * Returns the VPN type of the profile.
     */
    public abstract VpnType getType();
    void setCustomized(boolean customized) {
        mIsCustomized = customized;
    }
    protected void readFromParcel(Parcel in) {
        mName = in.readString();
        mId = in.readString();
        mServerName = in.readString();
        mDomainSuffices = in.readString();
        mRouteList = in.readString();
        mSavedUsername = in.readString();
    }
    public static final Parcelable.Creator<VpnProfile> CREATOR =
            new Parcelable.Creator<VpnProfile>() {
                public VpnProfile createFromParcel(Parcel in) {
                    VpnType type = Enum.valueOf(VpnType.class, in.readString());
                    boolean customized = in.readInt() > 0;
                    VpnProfile p = new VpnManager(null).createVpnProfile(type,
                            customized);
                    if (p == null) return null;
                    p.readFromParcel(in);
                    return p;
                }
                public VpnProfile[] newArray(int size) {
                    return new VpnProfile[size];
                }
            };
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getType().toString());
        parcel.writeInt(mIsCustomized ? 1 : 0);
        parcel.writeString(mName);
        parcel.writeString(mId);
        parcel.writeString(mServerName);
        parcel.writeString(mDomainSuffices);
        parcel.writeString(mRouteList);
        parcel.writeString(mSavedUsername);
    }
    public int describeContents() {
        return 0;
    }
}