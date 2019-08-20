package com.sadmansarar.app.latemailsend.data.api.apiExt

import androidx.lifecycle.LiveData
import com.sadmansarar.app.latemailsend.data.api.network.AppExecutor
import com.sadmansarar.app.latemailsend.data.api.network.Resource
import com.singularitybd.app.something.data.api.apiExt.Executable
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Sadman Sarar on 2019-04-10.
 */
fun <T> Call<T>.asExecutable(
        executor: AppExecutor,
        onSuccess: ((T?) -> Unit)? = null,
        onFailed: ((code: Int, msg: String, failedResponse: Response<T>?) -> Unit)? = null
): Executable<Resource<T>, LiveData<Resource<T>>> {
    return ExecutableFromCall(
            this,
            executor,
            onSuccess,
            onFailed)

}

fun <From, T> convertToCalledExecutor(
        data: From,
        executor: AppExecutor,
        converter: ExecuteConverter<From, Call<T>?>,
        onSuccess: ((T?) -> Unit)? = null,
        onFailed: ((code: Int, msg: String, failedResponse: Response<T>?) -> Unit)? = null,
        resourceForError: (() -> T?)? = null
): CalledExecutor<From, T> {

    return CalledExecutor(
            data,
            executor,
            converter,
            onSuccess,
            onFailed,
            resourceForError
    )
}


