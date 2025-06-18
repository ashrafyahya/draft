import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.getcapacitor.testadminpermissions',
  appName: 'TestAdminPermissions',
  webDir: 'dist',
  plugins: {
    DeviceAdmin: {
      enabled: true
    }
  }
};

export default config;
