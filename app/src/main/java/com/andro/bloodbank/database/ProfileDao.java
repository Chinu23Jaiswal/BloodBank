package com.andro.bloodbank.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.andro.bloodbank.DonorProfile;

import java.util.List;

/**
 * Created by androdev700 on 10/11/17.
 */

@Dao
public interface ProfileDao {
    @Query("SELECT * FROM donorprofile")
    List<DonorProfile> getAll();

    @Query("SELECT * FROM donorprofile WHERE donorid IN (:userIds)")
    List<DonorProfile> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM donorprofile WHERE name LIKE :first LIMIT 1")
    DonorProfile findByName(String first);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DonorProfile... users);

    @Delete
    void delete(DonorProfile user);
}
