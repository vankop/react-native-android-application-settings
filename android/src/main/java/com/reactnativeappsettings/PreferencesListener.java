package com.reactnativeappsettings;

import com.facebook.react.bridge.Callback;
import android.content.SharedPreferences;

public class PreferencesListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private PreferenceChangedEventEmitter emitter;

    PreferencesListener(PreferenceChangedEventEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.emitter.emit(key);
    }
}
