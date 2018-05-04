package com.reactnativeappsettings;

import com.facebook.react.bridge.Callback;
import android.content.SharedPreferences;

public class PreferencesListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Callback callback;

    PreferencesListener(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.callback.invoke(key);
    }
}
