package com.reactnativeappsettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class RNAndroidNativeAppSettingsModule extends ReactContextBaseJavaModule {

    private final Context appContext;
    private final ReactContext reactContext;
    private PreferencesListener listener = null;

    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.appContext);
    }

    RNAndroidNativeAppSettingsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.appContext = reactContext.getApplicationContext();
    }

    @ReactMethod
    public void getString(String parameter, Promise promise) {
        SharedPreferences sharedPreferences = getPreferences();
        promise.resolve(sharedPreferences.getString(parameter, null));
    }

    @ReactMethod
    public void getBoolean(String parameter, Promise promise) {
        SharedPreferences sharedPreferences = getPreferences();
        promise.resolve(sharedPreferences.getBoolean(parameter, false));
    }

    @ReactMethod
    public void getInteger(String parameter, Promise promise) {
        SharedPreferences sharedPreferences = getPreferences();
        promise.resolve(sharedPreferences.getInt(parameter, -1));
    }

    @ReactMethod
    public void getFloat(String parameter, Promise promise) {
        SharedPreferences sharedPreferences = getPreferences();
        promise.resolve(sharedPreferences.getFloat(parameter, -1f));
    }

    @ReactMethod
    public void listenChanges() {
        if (listener == null) {
            listener = new PreferencesListener(new PreferenceChangedEventEmitter() {
                @Override
                public void emit(String key) {
                    WritableMap event = Arguments.createMap();
                    event.putString("key", key);
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("preferencesChanged", event);
                }
            });
            SharedPreferences preferences = getPreferences();
            preferences.registerOnSharedPreferenceChangeListener(listener);
        } else {
            throw new Error("Listener already exists");
        }
    }

    @ReactMethod
    public void unlistenChanges() {
        if (listener == null) {
            throw new Error("Listener does not exists");
        } else {
            SharedPreferences preferences = getPreferences();
            preferences.unregisterOnSharedPreferenceChangeListener(listener);
            listener = null;
        }
    }

    @Override
    public String getName() {
        return "RNAndroidNativeAppSettings";
    }
}