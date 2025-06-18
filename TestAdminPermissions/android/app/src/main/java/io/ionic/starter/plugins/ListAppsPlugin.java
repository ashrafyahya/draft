package io.ionic.starter.plugins;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.ArrayList;
import java.util.List;

import com.getcapacitor.PluginMethod;

@CapacitorPlugin(name = "ListApps")
public class ListAppsPlugin extends Plugin {
    @PluginMethod
    public void requestDeviceAdmin(PluginCall call) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName compName = new ComponentName(getContext(), com.getcapacitor.MyDeviceAdminReceiver.class);
        if (!devicePolicyManager.isAdminActive(compName)) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "This app requires device administrator privileges to perform certain actions.");
            getActivity().startActivity(intent);
            JSObject ret = new JSObject();
            ret.put("success", false);
            call.resolve(ret);
        } else {
            JSObject ret = new JSObject();
            ret.put("success", true);
            call.resolve(ret);
        }
    }

    @PluginMethod
    public void listInstalledApps(PluginCall call) {
        PackageManager pm = getContext().getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        ArrayList<JSObject> apps = new ArrayList<>();
        for (PackageInfo pkg : packages) {
            if ((pkg.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 || (pkg.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                JSObject app = new JSObject();
                app.put("appName", pm.getApplicationLabel(pkg.applicationInfo).toString());
                app.put("packageName", pkg.packageName);
                app.put("versionName", pkg.versionName);
                app.put("versionCode", Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? pkg.getLongVersionCode() : pkg.versionCode);
                apps.add(app);
            }
        }
        JSObject ret = new JSObject();
        ret.put("apps", apps);
        call.resolve(ret);
    }
}
