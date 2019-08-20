package com.sadmansarar.app.latemailsend.data.repository.implimentation

import com.sadmansarar.app.latemailsend.data.api.retrofit.WebService
import com.sadmansarar.app.latemailsend.data.db.dao.RepoDao
import com.sadmansarar.app.latemailsend.data.repository.interfaces.RepoRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Sadman Sarar on 9/10/17.
 * Repository class - uses NetworkBoundResource to load data from API
 */
@Singleton
class IRepoRepository
@Inject constructor(
        private val repoDao: RepoDao,
        private val webService: WebService
) : RepoRepository {

}