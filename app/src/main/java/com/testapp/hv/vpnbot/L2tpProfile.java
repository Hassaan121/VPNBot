package com.testapp.hv.vpnbot;

import android.annotation.SuppressLint;
import android.os.Parcel;

/**
 * Created by hv on 7/8/16.
 */
  @SuppressLint("ParcelCreator")
  public class L2tpProfile extends VpnProfile {
        private static final long serialVersionUID = 1L;
        private boolean mSecret;
        private String mSecretString;
        @Override
        public VpnType getType() {
            return VpnType.L2TP;
        }
        /**
         * Enables/disables the secret for authenticating tunnel connection.
         */
        public void setSecretEnabled(boolean enabled) {
            mSecret = enabled;
        }
        public boolean isSecretEnabled() {
            return mSecret;
        }
        public void setSecretString(String secret) {
            mSecretString = secret;
        }
        public String getSecretString() {
            return mSecretString;
        }
        @Override
        protected void readFromParcel(Parcel in) {
            super.readFromParcel(in);
            mSecret = in.readInt() > 0;
            mSecretString = in.readString();
        }
        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            super.writeToParcel(parcel, flags);
            parcel.writeInt(mSecret ? 1 : 0);
            parcel.writeString(mSecretString);
        }
    }
}
