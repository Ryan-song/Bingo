package com.ryan.bingo;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by air on 16/2/28.
 */
public class BingoApplication extends Application {
    public static Context AppContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        Fresco.initialize(AppContext);
    }
}
