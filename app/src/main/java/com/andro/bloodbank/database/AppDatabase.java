package com.andro.bloodbank.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by androdev700 on 10/11/17.
 */
@Database(entities = {DonorProfile.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProfileDao profileDao();
}
