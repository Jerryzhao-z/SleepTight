package fr.sleeptight.data.localdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import fr.sleeptight.data.localdb.Sleep;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SLEEP".
*/
public class SleepDao extends AbstractDao<Sleep, Long> {

    public static final String TABLENAME = "SLEEP";

    /**
     * Properties of entity Sleep.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StartTime = new Property(1, java.util.Date.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(2, java.util.Date.class, "endTime", false, "END_TIME");
        public final static Property Duration = new Property(3, Integer.class, "duration", false, "DURATION");
        public final static Property WakeupDuration = new Property(4, Integer.class, "wakeupDuration", false, "WAKEUP_DURATION");
        public final static Property RestlessDuration = new Property(5, Integer.class, "restlessDuration", false, "RESTLESS_DURATION");
        public final static Property Efficiency = new Property(6, Integer.class, "efficiency", false, "EFFICIENCY");
    };

    private DaoSession daoSession;


    public SleepDao(DaoConfig config) {
        super(config);
    }
    
    public SleepDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SLEEP\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"START_TIME\" INTEGER," + // 1: startTime
                "\"END_TIME\" INTEGER," + // 2: endTime
                "\"DURATION\" INTEGER," + // 3: duration
                "\"WAKEUP_DURATION\" INTEGER," + // 4: wakeupDuration
                "\"RESTLESS_DURATION\" INTEGER," + // 5: restlessDuration
                "\"EFFICIENCY\" INTEGER);"); // 6: efficiency
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SLEEP\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Sleep entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        java.util.Date startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindLong(2, startTime.getTime());
        }
 
        java.util.Date endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindLong(3, endTime.getTime());
        }
 
        Integer duration = entity.getDuration();
        if (duration != null) {
            stmt.bindLong(4, duration);
        }
 
        Integer wakeupDuration = entity.getWakeupDuration();
        if (wakeupDuration != null) {
            stmt.bindLong(5, wakeupDuration);
        }
 
        Integer restlessDuration = entity.getRestlessDuration();
        if (restlessDuration != null) {
            stmt.bindLong(6, restlessDuration);
        }
 
        Integer efficiency = entity.getEfficiency();
        if (efficiency != null) {
            stmt.bindLong(7, efficiency);
        }
    }

    @Override
    protected void attachEntity(Sleep entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Sleep readEntity(Cursor cursor, int offset) {
        Sleep entity = new Sleep( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)), // startTime
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // endTime
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // duration
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // wakeupDuration
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // restlessDuration
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6) // efficiency
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Sleep entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStartTime(cursor.isNull(offset + 1) ? null : new java.util.Date(cursor.getLong(offset + 1)));
        entity.setEndTime(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setDuration(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setWakeupDuration(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setRestlessDuration(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setEfficiency(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Sleep entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Sleep entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
