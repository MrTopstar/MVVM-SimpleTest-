package com.aceplussolutions.myosetpaing.mvvmtest.model;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface ContactModelDao {

    @Query("select * from Contact")
    LiveData<List<Contact>> getContact();

    @Insert(onConflict = REPLACE)
    void addContact(Contact contact);

    @Delete
    void delectContact(Contact contact);
}
