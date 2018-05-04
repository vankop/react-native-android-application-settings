
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
import AppSettings from 'react-native-android-native-app-settings';

const parameter1 = 'parameter1';
const parameter2 = 'parameter2';
const parameter3 = 'parameter3';

export default async function getAppSettings() {
    const [str, int, bool] = await Promise.all([
        AppSettings.getString(parameter1),
        AppSettings.getInteger(parameter2),
        AppSettings.getBoolean(parameter3)
    ]);

    return { param1: str, param2: int, param3: bool };
}
```
