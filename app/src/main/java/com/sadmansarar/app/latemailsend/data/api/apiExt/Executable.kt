package com.singularitybd.app.something.data.api.apiExt

/**
 * Created by Sadman Sarar on 2019-04-10.
 */
interface Executable<SyncReturn, AsyncReturn> {

    fun executeAsync(): AsyncReturn
    fun execute(): SyncReturn

}
