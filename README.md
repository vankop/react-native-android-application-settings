
# react-native-android-native-app-settings
API for getting Android app settings based on [developer guide](https://developer.android.com/guide/topics/ui/settings)

[![Version](https://img.shields.io/npm/v/react-native-android-native-app-settings.svg)](https://www.npmjs.com/package/react-native-android-native-app-settings)
[![Licence](https://img.shields.io/npm/l/react-native-android-native-app-settings.svg)](https://github.com/vankop/react-native-android-application-settings/blob/master/LICENSE)
[![Known Vulnerabilities](https://snyk.io/test/github/vankop/react-native-android-application-settings/badge.svg?targetFile=package.json)](https://snyk.io/test/github/vankop/react-native-android-application-settings?targetFile=package.json)

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

**Create preferences activity**

[Full developer guide](https://developer.android.com/guide/topics/ui/settings).
Don't forget to update `AndroidManifest.xml`, for instance:

```xml
<activity
            android:name=".SettingsActivity"
            android:label="@string/title_activity_settings">
            <intent-filter><!-- Use this intent filter to link an activity into your app’s page in Settings, more about app settings https://commonsware.com/blog/2016/08/17/application_preferences-security.html -->
                <action android:name="android.intent.action.APPLICATION_PREFERENCES" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            <intent-filter><!-- Use this intent filter to deep link this activity, more about deep link https://facebook.github.io/react-native/docs/linking.html#handling-deep-links -->
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="petapp" android:host="settings" />
            </intent-filter>
        </activity>
```
**Update MainApplication.java if you use default values**

If you have problems with preference type use [this guid](https://room-15.github.io/blog/2015/03/30/beware-of-preference-default-values-in-xml/).
```java
  @Override
  public void onCreate() {
    super.onCreate();
    ...
    PreferenceManager.setDefaultValues(this, R.xml.preference/** here is your preferences.xml **/, false);
  }
```

Now you can access preferences in JS thread, for instance:

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

## API

**`getString(String parameter): String`**

+ `parameter` - Android preference `key`( in xml `android:key`)
+ returns `parameter` preference as `string`

**`getBoolean(String parameter): Boolean`**

+ `parameter` - Android preference `key`( in xml `android:key`)
+ returns `parameter` preference as `boolean`

**`getInteger(String parameter): Number`**

+ `parameter` - Android preference `key`( in xml `android:key`)
+ returns `parameter` preference as `number`

**`getFloat(String parameter): Number`**

+ `parameter` - Android preference `key`( in xml `android:key`)
+ returns `parameter` preference as `number`

Difference between `getFloat` and `getInteger` is in preference type.
So if you try `getFloat` from integer value, Java will cast it to float and vice versa.

**`listenChanges()`**

+ throws error if emitter already exists

Adding event emitter for `preferencesChanged` event. It will emit event each time preference changed.
`event.key` - Android preference `key`.

Usage:

```javascript
import AppSettings from 'react-native-android-native-app-settings';
import { DeviceEventEmitter } from 'react-native';

function listener({ key }) {
    if (key === 'some') {
         AppSettings.getString('some');
    }
}

export function listenSettings() {
    AppSettings.listenChanges();
    DeviceEventEmitter.addListener('preferencesChanged', listener);
}

export function unlistenSettings() {
    AppSettings.unlistenChanges();
    DeviceEventEmitter.removeListener('preferencesChanged', listener);
}

```

**`unlistenChanges()`**

+ throws error if emitter does not listen changes

Stops event emitter.
