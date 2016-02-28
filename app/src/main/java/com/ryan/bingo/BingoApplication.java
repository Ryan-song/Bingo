package com.ryan.bingo;

import android.app.Application;
import android.content.Context;

/**
 * Created by air on 16/2/28.
 */
public class BingoApplication extends Application {
    public static Context AppContent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContent = getApplicationContext();
    }
}
