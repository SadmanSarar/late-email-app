package com.sadmansarar.app.latemailsend.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sadmansarar.app.latemailsend.data.db.dao.RepoDao
import com.sadmansarar.app.latemailsend.data.model.room.Repo

/**
 * Created by Sadman Sarar on 9/9/17.
 * Database Class including the Dao
 */
@Database(entities = [(Repo::class)], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}

