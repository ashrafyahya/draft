package com.testadminpermissions.app;

import android.os.Bundle;
import android.util.Log;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    private static final String TAG = "Capacitor/MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            Log.d(TAG, "Registering DeviceAdmin plugin");
            registerPlugin(DeviceAdminPlugin.class);
            Log.d(TAG, "DeviceAdmin plugin registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error registering DeviceAdmin plugin: " + e.getMessage(), e);
        }
        super.onCreate(savedInstanceState);
    }
}
