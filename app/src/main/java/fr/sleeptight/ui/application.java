package fr.sleeptight.ui;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.yolanda.nohttp.NoHttp;

/**
 * Created by User on 6/5/2016.
 */
public class application extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        ContextHolder.init(this.getApplicationContext());
        NoHttp.init(this);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
