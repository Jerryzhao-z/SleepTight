package fr.sleeptight.data.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by UserUtils on 6/12/2016.
 */
public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "sleeptight.sqlite";  //声明数据库
    private volatile static DaoManager manager; //多线程名称
    private static DaoMaster.DevOpenHelper helper;
    private static SQLiteDatabase database;
    private static DaoMaster daomaster;
    private static DaoSession daoSession;

    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 1 数据库类的创建
     * 线程安全创建DaoManager,使用单例模式获得操作数据库的对象
     *
     * @return note:为保证数据库的有效性，采用单利模式进行访问
     */
    public static DaoManager getInstance() {
        DaoManager instance = null;
        if (manager == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                    manager = instance;
                }
            }
        }
        return manager;
    }

    public void OnCreate()
    {
        this.helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        this.database = helper.getWritableDatabase();
        this.daomaster = new DaoMaster(database);
        this.daoSession = this.daomaster.newSession();
    }

    /**
     * 2 Master类的创建
     * 得到DaoMaster
     *
     * @return note：系统帮助用户监测是否有数据库，如果没有，则创建数据库
     */
    public DaoMaster getDaoMaster() {
        if (daomaster == null) {
            helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            this.database = helper.getWritableDatabase();
            daomaster = new DaoMaster(this.database);
        }
        return daomaster;
    }

    public SQLiteDatabase getDatabase()
    {
        if (database == null) {
            helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            this.database = helper.getWritableDatabase();
            daomaster = new DaoMaster(this.database);
        }
        return database;
    }
    /**
     * 3 数据库会话层DaoSession的创建
     * 完成对数据库的增删改查的操作，这里仅仅是一个接口。
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daomaster == null) {
                daomaster = getDaoMaster();
            }
            daoSession = daomaster.newSession();
        }
        return daoSession;
    }



    /**
     * 关闭数据库的操作，使用完毕数据库，必须执行此操作。
     */
    public void CloseConnection() {
        CloseHelper();
        CloseDaoSession();
    }

    /**
     * 关闭helper
     */
    public void CloseHelper() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    /**
     * 关闭Session会话层
     */
    public void CloseDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }


    /**
     * 打开输出日志的操作
     */
    public void SetDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

    }

}
