package com.example.myapplication.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Student.class,Address.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    public static synchronized AppDatabase getInstance(Context context){
        if (null == appDatabase){
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"zijie")
                    .allowMainThreadQueries()
//                    .addMigrations()
                    //正常的应该用这个
//                    .addMigrations(MIGRATION_2_3)
                    //强制升级旧数据会丢失
//                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
    static final Migration MIGRATION_2_3 =new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table student add column flag2 integer not null default 1");
        }
    };
    public abstract StudentDao userDao();
}






