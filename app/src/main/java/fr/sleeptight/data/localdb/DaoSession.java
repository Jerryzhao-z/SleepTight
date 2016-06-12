package fr.sleeptight.data.localdb;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import fr.sleeptight.data.localdb.Event;
import fr.sleeptight.data.localdb.User;
import fr.sleeptight.data.localdb.Sleep;
import fr.sleeptight.data.localdb.SleepDetail;

import fr.sleeptight.data.localdb.EventDao;
import fr.sleeptight.data.localdb.UserDao;
import fr.sleeptight.data.localdb.SleepDao;
import fr.sleeptight.data.localdb.SleepDetailDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig eventDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig sleepDaoConfig;
    private final DaoConfig sleepDetailDaoConfig;

    private final EventDao eventDao;
    private final UserDao userDao;
    private final SleepDao sleepDao;
    private final SleepDetailDao sleepDetailDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        eventDaoConfig = daoConfigMap.get(EventDao.class).clone();
        eventDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        sleepDaoConfig = daoConfigMap.get(SleepDao.class).clone();
        sleepDaoConfig.initIdentityScope(type);

        sleepDetailDaoConfig = daoConfigMap.get(SleepDetailDao.class).clone();
        sleepDetailDaoConfig.initIdentityScope(type);

        eventDao = new EventDao(eventDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        sleepDao = new SleepDao(sleepDaoConfig, this);
        sleepDetailDao = new SleepDetailDao(sleepDetailDaoConfig, this);

        registerDao(Event.class, eventDao);
        registerDao(User.class, userDao);
        registerDao(Sleep.class, sleepDao);
        registerDao(SleepDetail.class, sleepDetailDao);
    }
    
    public void clear() {
        eventDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
        sleepDaoConfig.getIdentityScope().clear();
        sleepDetailDaoConfig.getIdentityScope().clear();
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public SleepDao getSleepDao() {
        return sleepDao;
    }

    public SleepDetailDao getSleepDetailDao() {
        return sleepDetailDao;
    }

}
