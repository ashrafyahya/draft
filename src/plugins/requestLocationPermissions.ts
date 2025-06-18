import { Geolocation } from '@capacitor/geolocation';
import DeviceAdmin from './DeviceAdminPlugin';

export async function requestLocationPermissions() {
  try {
    // First request device admin permissions
    const adminResult = await DeviceAdmin.requestDeviceAdmin();
    if (!adminResult.granted) {
      return { location: 'denied', error: 'Device admin permission not granted' };
    }

    // Then request location permissions
    const permission = await Geolocation.checkPermissions();
    if (permission.location === 'prompt') {
      return await Geolocation.requestPermissions();
    }
    return permission;
  } catch (e) {
    return { location: 'denied', error: e };
  }
}
