package com.byteshaft.idsender;


import android.app.Application;
import android.content.Context;

public class AppGlobals extends Application {
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }
}
