import { registerPlugin } from '@capacitor/core';

export interface AppInfo {
  appName: string;
  packageName: string;
  versionName: string;
  versionCode: number;
}

export interface ListAppsPlugin {
  listInstalledApps(): Promise<{ apps: AppInfo[] }>;
  requestDeviceAdmin(): Promise<{ success: boolean }>;
}

const ListApps = registerPlugin<ListAppsPlugin>('ListApps');
export default ListApps;
