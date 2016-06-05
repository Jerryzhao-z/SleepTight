package fr.sleeptight.ui;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

/**
 * Created by User on 6/5/2016.
 */
public class application extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        NoHttp.init(this);
    }
}
