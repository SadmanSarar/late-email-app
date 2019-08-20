package com.sadmansarar.app.latemailsend.data.api.network

import javax.inject.Inject

/**
 * Created by Sadman Sarar on 10/15/18.
 */
class IAppExecutor @Inject constructor() : AppExecutor {
    override fun ioThread(f: () -> Unit) {
        appIoThread(f)
    }

    override fun networkThread(f: () -> Unit) {
        appNetworkThread(f)
    }

    override fun mainThread(f: () -> Unit) {
        appMainThread(f)
    }
}