package com.andro.bloodbank.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by andro on 10/11/17.
 */

@Dao
public interface ProfileDao {
    @Query("SELECT * FROM donorprofile")
    List<DonorProfile> getAll();

    @Query("SELECT * FROM donorprofile WHERE donorid LIKE :donorId")
    DonorProfile getDonorById(String donorId);

    @Query("SELECT * FROM donorprofile WHERE name LIKE :name LIMIT 1")
    DonorProfile findByName(String name);

    @Query("SELECT * FROM donorprofile WHERE bloodgroup LIKE :bloodGroup")
    List<DonorProfile> getDonorWithBloodGroup(String bloodGroup);

    @Query("SELECT * FROM donorprofile WHERE rhtype LIKE :rhtype")
    List<DonorProfile> getDonorWithRhType(String rhtype);

    @Query("SELECT * FROM donorprofile WHERE age BETWEEN :age1 AND :age2")
    List<DonorProfile> getDonorsInAgeRange(String age1, String age2);

    @Query("SELECT * FROM donorprofile WHERE gender LIKE :gender")
    List<DonorProfile> getDonorsWithGender(String gender);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(DonorProfile... users);

    @Delete
    void delete(DonorProfile user);

    @Query("DELETE FROM donorprofile")
    void deleteAll();
}
