package fr.sleeptight.ui;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.yolanda.nohttp.NoHttp;

import fr.sleeptight.data.acces.CONSTANT;
import fr.sleeptight.data.localdb.DaoManager;
import fr.sleeptight.data.localdb.DaoMaster;

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
        DaoManager manager = DaoManager.getInstance();
        manager.init(this.getApplicationContext());
        manager.OnCreate();
        manager.getDaoMaster().createAllTables(manager.getDatabase(), true);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
