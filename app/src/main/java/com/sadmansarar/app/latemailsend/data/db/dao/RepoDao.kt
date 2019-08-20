package com.sadmansarar.app.latemailsend.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadmansarar.app.latemailsend.data.model.room.Repo

/**
 * Created by Sadman Sarar on 10/15/18.
 */
@Dao
abstract class RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repositories: List<Repo>)

    @Query("SELECT * FROM Repo "
            + "WHERE lower(owner_login) = lower(:owner)"
            + "ORDER BY stars DESC")
    abstract fun loadRepositories(owner: String): LiveData<List<Repo>>

}