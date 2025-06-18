package com.getcapacitor;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.NonNull;


public class MyDeviceAdminReceiver extends DeviceAdminReceiver {

    @Override
    public void onEnabled(@NonNull Context context, @NonNull Intent intent) {
        super.onEnabled(context, intent);
        Toast.makeText(context, "Device Admin: Enabled", Toast.LENGTH_SHORT).show();
        // Add any specific logic you need when your app is enabled as a device admin
    }

    @Override
    public CharSequence onDisableRequested(@NonNull Context context, @NonNull Intent intent) {
        // This is called when the user attempts to disable your app as a device admin.
        // You can return a message to the user explaining why your app needs device admin privileges.
        return "Disabling this app as a device administrator will limit its functionality.";
    }

    @Override
    public void onDisabled(@NonNull Context context, @NonNull Intent intent) {
        super.onDisabled(context, intent);
        Toast.makeText(context, "Device Admin: Disabled", Toast.LENGTH_SHORT).show();
        // Add any specific logic you need when your app is disabled as a device admin
    }

    @Override
    public void onPasswordChanged(@NonNull Context context, @NonNull Intent intent) {
        super.onPasswordChanged(context, intent);
        // Called after the user changes their device password/PIN/pattern
        // You might want to react to this if your app deals with security policies
    }

    @Override
    public void onPasswordFailed(@NonNull Context context, @NonNull Intent intent) {
        super.onPasswordFailed(context, intent);
        // Called after an unsuccessful attempt to unlock the device
    }

    @Override
    public void onPasswordSucceeded(@NonNull Context context, @NonNull Intent intent) {
        super.onPasswordSucceeded(context, intent);
        // Called after a successful attempt to unlock the device
    }

    // You can override other callbacks as needed, for example:
    // onPasswordExpiring, onProfileProvisioningComplete, onBugreportShared, etc.
    // Refer to the official documentation for a full list of available callbacks:
    // https://developer.android.com/reference/android/app/admin/DeviceAdminReceiver

}
