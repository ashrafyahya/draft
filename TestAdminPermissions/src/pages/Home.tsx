import { Geolocation } from '@capacitor/geolocation';
import { IonContent, IonHeader, IonItem, IonLabel, IonList, IonPage, IonTitle, IonToolbar } from '@ionic/react';
import React, { useEffect, useState } from 'react';
import ListApps, { AppInfo } from '../plugins/ListApps';
import './Home.css';

const Home: React.FC = () => {
  const [apps, setApps] = useState<AppInfo[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const checkAndRequestAdmin = async () => {
    try {
      const result = await ListApps.requestDeviceAdmin();
      return result.success;
    } catch (e) {
      setError('Failed to request admin permission.');
      return false;
    }
  };

  const fetchApps = async () => {
    try {
      const result = await ListApps.listInstalledApps();
      setApps(result.apps);
    } catch (e) {
      setError('Failed to fetch installed apps.');
    }
  };

  const checkAndRequestLocation = async () => {
    try {
      const permResult = await Geolocation.requestPermissions();
      return permResult.location === 'granted';
    } catch {
      setError('Failed to request location permission.');
      return false;
    }
  };

  useEffect(() => {
    const init = async () => {
      setLoading(true);
      setError(null);
      const adminOk = await checkAndRequestAdmin();
      if (!adminOk) {
        setLoading(false);
        setError('Device admin permission not granted.');
        return;
      }
      const locationOk = await checkAndRequestLocation();
      if (!locationOk) {
        setLoading(false);
        setError('Location permission not granted.');
        return;
      }
      await fetchApps();
      setLoading(false);
    };
    init();
  }, []);

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Installed Apps</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        {loading && <IonLabel>Loading...</IonLabel>}
        {error && <IonLabel color="danger">{error}</IonLabel>}
        {!loading && !error && (
          <IonList>
            {apps.map(app => (
              <IonItem key={app.packageName}>
                <IonLabel>{app.appName} ({app.packageName})</IonLabel>
              </IonItem>
            ))}
          </IonList>
        )}
      </IonContent>
    </IonPage>
  );
};

export default Home;
