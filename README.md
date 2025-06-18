# TestAdminPermissions

## Project Idea

This project is an Android application built using React and Ionic. The main goal of the app is to demonstrate and test the ability to request and utilize admin-level permissions on Android devices. The app aims to access and interact with other apps on the device, which requires elevated (admin) permissions.

## How the Project Was Created

1. **Initialize the Project**: The project was created using the Ionic CLI with React as the framework.
2. **Add Android Platform**: The Android platform was added using Capacitor, which allows the app to be built and run as a native Android application.
3. **Configure Permissions**: The app is configured to request device admin permissions, which are necessary for accessing and managing other apps.
4. **Build and Test**: The app can be built and tested using Android Studio.

## How to Build Using Android Studio

1. **Install Prerequisites**:
   - Node.js and npm
   - Ionic CLI (`npm install -g @ionic/cli`)
   - Android Studio

2. **Install Dependencies**:
   - Run `npm install` in the project directory to install all dependencies.

3. **Build the App**:
   - Run `ionic build` to build the web assets.
   - Run `npx cap sync android` to sync the web assets and native code.

4. **Open in Android Studio**:
   - Run `npx cap open android` to open the Android project in Android Studio.

5. **Run the App**:
   - Use Android Studio to build and run the app on an emulator or a physical device.

## Note

Requesting admin permissions and accessing other apps requires special handling and user consent. Make sure to follow Android's guidelines and best practices for permissions and security.

------
npm run dev

npm run build
npx cap sync android
