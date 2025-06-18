package com.testadminpermissions.app;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.JSObject;

@CapacitorPlugin(name = "DeviceAdmin")
public class DeviceAdminPlugin extends Plugin {
    private static final String TAG = "Capacitor/DeviceAdmin";
    private static final int REQUEST_CODE_ENABLE_ADMIN = 1;

    public DeviceAdminPlugin() {
        Log.d(TAG, "DeviceAdminPlugin constructor called");
    }

    @Override
    public void load() {
        super.load();
        Log.d(TAG, "DeviceAdminPlugin loaded");
    }

    @PluginMethod
    public void enable(PluginCall call) {
        Log.d(TAG, "Enable method called");
        Context context = getContext();
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName compName = new ComponentName(context, AppDeviceAdminReceiver.class);
        
        Log.d(TAG, "Checking if admin is already active");
        if (dpm.isAdminActive(compName)) {
            Log.d(TAG, "Admin is already active");
            JSObject ret = new JSObject();
            ret.put("granted", true);
            call.resolve(ret);
            return;
        }

        Log.d(TAG, "Admin not active, requesting admin privileges");
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Need device admin permission for secure functionality");
        
        saveCall(call);
        try {
            startActivityForResult(call, intent, REQUEST_CODE_ENABLE_ADMIN);
            Log.d(TAG, "Admin request activity started");
        } catch (Exception e) {
            Log.e(TAG, "Error starting admin request activity: " + e.getMessage(), e);
            call.reject("Failed to start admin request: " + e.getMessage());
        }
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Activity result received. RequestCode: " + requestCode + ", ResultCode: " + resultCode);

        if (requestCode == REQUEST_CODE_ENABLE_ADMIN) {
            PluginCall savedCall = getSavedCall();
            if (savedCall == null) {
                Log.e(TAG, "No saved call found for admin request result");
                return;
            }

            Context context = getContext();
            DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
            ComponentName compName = new ComponentName(context, AppDeviceAdminReceiver.class);

            boolean isAdmin = dpm.isAdminActive(compName);
            Log.d(TAG, "Admin status after activity result: " + isAdmin);

            JSObject ret = new JSObject();
            ret.put("granted", isAdmin);
            savedCall.resolve(ret);
            freeSavedCall();
        }
    }
}
