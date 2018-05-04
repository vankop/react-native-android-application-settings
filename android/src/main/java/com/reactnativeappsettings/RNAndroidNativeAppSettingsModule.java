package com.reactnativeappsettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNAndroidNativeAppSettingsModule extends ReactContextBaseJavaModule {

    private final Context appContext;
    private PreferencesListener listener = null;

    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.appContext);
    }

    RNAndroidNativeAppSettingsModule(ReactApplicationContext reactContext) {
        super(reactContext);
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
    public void listenChanges(Callback callback) {
        if (listener == null) {
            listener = new PreferencesListener(callback);
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