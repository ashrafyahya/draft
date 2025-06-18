import { registerPlugin } from '@capacitor/core';

export interface DeviceAdminPlugin {
  requestDeviceAdmin(): Promise<{ granted: boolean }>;
}

const DeviceAdmin = registerPlugin<DeviceAdminPlugin>('DeviceAdmin');

export default DeviceAdmin;
