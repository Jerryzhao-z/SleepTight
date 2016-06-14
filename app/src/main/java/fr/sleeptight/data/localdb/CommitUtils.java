package fr.sleeptight.data.localdb;

/**
 * Created by User on 6/12/2016.
 */
import android.content.Context;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import fr.sleeptight.ui.ContextHolder;

public class CommitUtils {
    private DaoManager manager;
    private CommitUtils commitUtils;

    public CommitUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }


    //Event
    /**
     * 完成对数据库表的插入操作
     *
     * @param event
     * @return
     */
    public boolean insertEvent(Event event) {
        boolean flag = false;
        flag = manager.getDaoSession().insertOrReplace(event) != -1 ? true : false;
        return flag;
    }

    /**
     * 完成对数据库的多次插入
     *
     * @param events
     * @return
     */
    public boolean insertMultEvent(final List<Event> events) {
        boolean flag = false;

        try { // 启动一个线程，执行多次插入

            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Event event : events) {
                        manager.getDaoSession().insertOrReplace(event);
                    }

                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新对student某一条记录的修改
     * @param event
     * @return
     */
    public boolean updateEvent(Event event) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(event);
            flag = true;
        } catch (Exception e)

        {
            e.printStackTrace();
        }


        return flag;
    }


    /**
     * 删除一条数据
     * @param event
     * @return
     */
    public boolean deletEvent(Event event) {

        boolean flag = false;
        try {
            // 删除一条记录
            manager.getDaoSession().delete(event);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        manager.getDaoSession().deleteAll(Student.class);

        return flag;

    }
//

    /**
     * 返回多行记录
     * @return
     */
    public List<Event> ListAllEvent() {
        return manager.getDaoSession().loadAll(Event.class);
    }

    /**
     * 按照主键返回单行记录
     * @param key
     * @return
     */
    public Event ListOneEvent(long key) {
        return manager.getDaoSession().load(Event.class, key);
    }


/*    *//**
     * 查询数据 条件查询
     * @return
     *//*
    public   List<Event> Query1() {


        //return manager.getDaoSession().queryRaw(Event.class, "where name like ? and _id > ?", new String[]{"%张三%", "2"});

    }

    *//**
     * 查询数据 对于数据库不熟悉可使用这种方式
     * @return
     *//*
    public List<Event> Query() {

        QueryBuilder<Event> builder = manager.getDaoSession().queryBuilder(Event.class);
        //List<Event> list = builder.where(EventDao.Properties.Age.between(23, 26))
        //        .where(StudentDao.Properties.Address.like("北京")).list();
//        builder.orderAsc(StudentDao.Properties.Age);

        //return list;
    }*/


    //Sleep
    /**
     * 完成对数据库表的插入操作
     *
     * @param sleep
     * @return
     */
    public boolean insertSleep(Sleep sleep) {
        boolean flag = false;
        flag = manager.getDaoSession().insertOrReplace(sleep) != -1 ? true : false;
        return flag;
    }

    /**
     * 完成对数据库的多次插入
     *
     * @param sleeps
     * @return
     */
    public boolean insertMultSleep(final List<Sleep> sleeps) {
        boolean flag = false;

        try { // 启动一个线程，执行多次插入

            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Sleep sleep : sleeps) {
                        manager.getDaoSession().insertOrReplace(sleep);
                    }

                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新对student某一条记录的修改
     * @param sleep
     * @return
     */
    public boolean updateSleep(Sleep sleep) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(sleep);
            flag = true;
        } catch (Exception e)

        {
            e.printStackTrace();
        }


        return flag;
    }


    /**
     * 删除一条数据
     * @param sleep
     * @return
     */
    public boolean deleteSleep(Sleep sleep) {

        boolean flag = false;
        try {
            // 删除一条记录
            manager.getDaoSession().delete(sleep);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        manager.getDaoSession().deleteAll(Student.class);

        return flag;

    }
//

    /**
     * 返回多行记录
     * @return
     */
    public List<Sleep> ListAllSleep() {
        return manager.getDaoSession().loadAll(Sleep.class);
    }

    /**
     * 按照主键返回单行记录
     * @param key
     * @return
     */
    public Sleep ListOneSleep(long key) {
        return manager.getDaoSession().load(Sleep.class, key);
    }

    public List<Sleep> QuerySleepSpecifiqueTime(Date startTime, Date dateOfSleep) {
        QueryBuilder<Sleep> builder = manager.getDaoSession().queryBuilder(Sleep.class);
        List<Sleep> list = builder.where(SleepDao.Properties.DateOfSleep.eq(dateOfSleep))
                .where(SleepDao.Properties.StartTime.eq(startTime)).list();
        return list;
    }

    public List<Sleep> QuerySleepSpecifitqueDay(Date dateOfSleep) {
        QueryBuilder<Sleep> builder = manager.getDaoSession().queryBuilder(Sleep.class);
        List<Sleep> list = builder.where(SleepDao.Properties.DateOfSleep.eq(dateOfSleep)).list();
        return list;
    }

/*    *//**
     * 查询数据 条件查询
     * @return
     *//*
    public   List<Event> Query1() {


        //return manager.getDaoSession().queryRaw(Event.class, "where name like ? and _id > ?", new String[]{"%张三%", "2"});

    }

    *//**
     * 查询数据 对于数据库不熟悉可使用这种方式
     * @return
     *//*
    public List<Event> Query() {

        QueryBuilder<Event> builder = manager.getDaoSession().queryBuilder(Event.class);
        //List<Event> list = builder.where(EventDao.Properties.Age.between(23, 26))
        //        .where(StudentDao.Properties.Address.like("北京")).list();
//        builder.orderAsc(StudentDao.Properties.Age);

        //return list;
    }*/

    //SleepDetail
    /**
     * 完成对数据库表的插入操作
     *
     * @param sleepdetail
     * @return
     */
    public boolean insertSleepDetail(SleepDetail sleepdetail) {
        boolean flag = false;
        flag = manager.getDaoSession().insertOrReplace(sleepdetail) != -1 ? true : false;
        return flag;
    }

    /**
     * 完成对数据库的多次插入
     *
     * @param sleepdetails
     * @return
     */
    public boolean insertMultSleepDetail(final List<SleepDetail> sleepdetails) {
        boolean flag = false;

        try { // 启动一个线程，执行多次插入

            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (SleepDetail sleepdetail : sleepdetails) {
                        manager.getDaoSession().insertOrReplace(sleepdetail);
                    }

                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新对student某一条记录的修改
     * @param sleepdetail
     * @return
     */
    public boolean updateSleepDetail(SleepDetail sleepdetail) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(sleepdetail);
            flag = true;
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        return flag;
    }


    /**
     * 删除一条数据
     * @param sleepDetail
     * @return
     */
    public boolean deleteSleepDetail(SleepDetail sleepDetail) {

        boolean flag = false;
        try {
            // 删除一条记录
            manager.getDaoSession().delete(sleepDetail);

            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        manager.getDaoSession().deleteAll(Student.class);

        return flag;

    }

//

    /**
     * 返回多行记录
     * @return
     */
    public List<SleepDetail> ListAllSleepDetail() {
        return manager.getDaoSession().loadAll(SleepDetail.class);
    }

    /**
     * return sleepDetails of one night
     * @return
     */
    public List<SleepDetail> ListSleepDetailsOfSleep(Long SleepId) {
        QueryBuilder<SleepDetail> builder = manager.getDaoSession().queryBuilder(SleepDetail.class);
        List<SleepDetail> list = builder.where(SleepDetailDao.Properties.SleepId.eq(SleepId)).list();
        return list;
    }

    /**
     * 按照主键返回单行记录
     * @param key
     * @return
     */
    public SleepDetail ListOneSleepDetail(long key) {
        return manager.getDaoSession().load(SleepDetail.class, key);
    }


/*    *//**
     * 查询数据 条件查询
     * @return
     *//*
    public   List<Event> Query1() {


        //return manager.getDaoSession().queryRaw(Event.class, "where name like ? and _id > ?", new String[]{"%张三%", "2"});

    }

    *//**
     * 查询数据 对于数据库不熟悉可使用这种方式
     * @return
     *//*
    public List<Event> Query() {

        QueryBuilder<Event> builder = manager.getDaoSession().queryBuilder(Event.class);
        //List<Event> list = builder.where(EventDao.Properties.Age.between(23, 26))
        //        .where(StudentDao.Properties.Address.like("北京")).list();
//        builder.orderAsc(StudentDao.Properties.Age);

        //return list;
    }*/

}