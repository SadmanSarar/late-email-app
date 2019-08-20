package com.sadmansarar.app.latemailsend.viewmodel.implimentation

import androidx.lifecycle.ViewModel
import com.sadmansarar.app.latemailsend.data.repository.interfaces.RepoRepository
import com.sadmansarar.app.latemailsend.viewmodel.interfaces.RepoViewModel
import javax.inject.Inject


/**
 * Created by Sadman Sarar on 9/10/17.
 * ViewModel for the Repos
 */
class IRepoViewModel
@Inject constructor(
        private val mRepo: RepoRepository
) : ViewModel(), RepoViewModel {

}