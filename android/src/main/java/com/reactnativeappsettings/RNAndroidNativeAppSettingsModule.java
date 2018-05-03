package com.reactnativeappsettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNAndroidNativeAppSettingsModule extends ReactContextBaseJavaModule {

    private final Context appContext;

    protected SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this.appContext);
    }

    public RNAndroidNativeAppSettingsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.appContext = reactContext.getApplicationContext();
    }

    @ReactMethod
    public void getString(String parameter, Promise promise) {
        SharedPreferences sharedPreferences = this.getPreferences();
        promise.resolve(sharedPreferences.getString(parameter, ""));
    }

    @ReactMethod
    public void getAll(Promise promise) {
        SharedPreferences sharedPreferences = this.getPreferences();
        promise.resolve(sharedPreferences.getAll());
    }

    @ReactMethod
    public void getBoolean(String parameter, Promise promise) {
        SharedPreferences sharedPreferences = this.getPreferences();
        promise.resolve(sharedPreferences.getBoolean(parameter, false));
    }

    @ReactMethod
    public void getInteger(String parameter, Promise promise) {
        SharedPreferences sharedPreferences = this.getPreferences();
        promise.resolve(sharedPreferences.getInt(parameter, -1));
    }

    @Override
    public String getName() {
        return "RNAndroidNativeAppSettings";
    }
}