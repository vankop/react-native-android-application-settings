
# react-native-android-native-app-settings

## Getting started

`npm install react-native-android-native-app-settings --save`

### Mostly automatic installation

`react-native link react-native-android-native-app-settings`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNAndroidNativeAppSettingsPackage;` to the imports at the top of the file
  - Add `new RNAndroidNativeAppSettingsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-native-app-settings'
  	project(':react-native-android-native-app-settings').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-native-app-settings/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-native-app-settings')
  	```

## Usage

**settings.android.js**

```javascript
import RNAndroidNativeAppSettings from 'react-native-android-native-app-settings';

export default async function getAppSettings() {
    return await RNAndroidNativeAppSettings.getAll();
}
```
