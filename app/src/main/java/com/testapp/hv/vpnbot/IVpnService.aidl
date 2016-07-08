package com.testapp.hv.vpnbot;
import android.net.vpn.VpnProfile;
/**
 * Interface to access a VPN service.
 * {@hide}
 */
interface IVpnService {
    /**
     * Sets up the VPN connection.
     * @param profile the profile object
     * @param username the username for authentication
     * @param password the corresponding password for authentication
     */
    boolean connect(in VpnProfile profile, String username, String password);
    /**
     * Tears down the VPN connection.
     */
    void disconnect();
    /**
     * Makes the service broadcast the connectivity state.
     */
    void checkStatus(in VpnProfile profile);
}